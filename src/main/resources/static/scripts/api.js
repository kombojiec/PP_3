function getUsersList(role) {
    return fetch("/api/users", {
        headers: {
            'isAdmin': role
        }
    })
}

function getUserInfo(id) {
    return fetch(`/api/users/${id}`);
}

function createUser(obj, role) {
    return fetch(`/api/users`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
            'isAdmin': role
        },
        body: JSON.stringify(obj)
    })
}

function updateUser(obj, role) {
    return fetch('/api/users', {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json',
            'isAdmin': role
        },
        body: JSON.stringify(obj)
    })
}

function deleteUser(id, role) {
    return fetch(`/api/users/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json',
            'isAdmin': role
        }
    })
}

export {getUsersList, getUserInfo, createUser, updateUser, deleteUser};