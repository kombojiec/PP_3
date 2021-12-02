function getUsersList() {
    return fetch("/api/users")
}

function getUserInfo(id) {
    return fetch(`/api/users/${id}`);
}

function createUser(obj) {
    return fetch(`/api/users`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(obj)
    })
}

function updateUser(obj) {
    return fetch('/api/users', {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(obj)
    })
}

function deleteUser(id) {
    return fetch(`/api/users/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        }
    })
}

export {getUsersList, getUserInfo, createUser, updateUser, deleteUser};