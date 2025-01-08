let windows = [];
let rooms = [];

const BASE_URL = 'http://localhost:8080';  // URL for the backend
const USERNAME = 'user';  // Your username
const PASSWORD = 'password';  // Your password

// Encode username and password in base64
const encodedCredentials = btoa(`${USERNAME}:${PASSWORD}`);

window.onload = function() {
    // Fetch rooms and windows when the page loads
    fetchRooms();
    fetchWindows();
};

function fetchRooms() {
    fetch(`${BASE_URL}/api/rooms`, {
        method: 'GET',
        headers: {
            'Authorization': `Basic ${encodedCredentials}`,  // Attach the basic auth header
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            rooms = data;
            const roomFilter = document.getElementById('roomFilter');
            
            // Populate room dropdown with room names
            rooms.forEach(room => {
                const option = document.createElement('option');
                option.value = room.id;
                option.textContent = room.name;
                roomFilter.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching rooms:', error));
}

function fetchWindows() {
    fetch(`${BASE_URL}/api/windows`, {
        method: 'GET',
        headers: {
            'Authorization': `Basic ${encodedCredentials}`,  // Attach the basic auth header
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            windows = data;
            displayWindows(windows);  // Display all windows initially
        })
        .catch(error => console.error('Error fetching windows:', error));
}

function displayWindows(windowsList) {
    const windowItems = document.getElementById('windowItems');
    windowItems.innerHTML = '';  // Clear previous list

    windowsList.forEach(window => {
        const listItem = document.createElement('li');
        listItem.classList.add(window.windowStatus === 1 ? 'active' : 'inactive');
        listItem.innerHTML = `
            <strong>${window.name}</strong><br>
            Status: ${window.windowStatus === 1 ? 'Open' : 'Closed'}
        `;
        windowItems.appendChild(listItem);
    });
}

function filterWindows() {
    const selectedRoomId = document.getElementById('roomFilter').value;
    
    // If "All Rooms" is selected, show all windows
    if (selectedRoomId === 'all') {
        displayWindows(windows);
        return;
    }

    // Filter windows by room
    const filteredWindows = windows.filter(window => window.roomId == selectedRoomId);
    displayWindows(filteredWindows);
}
