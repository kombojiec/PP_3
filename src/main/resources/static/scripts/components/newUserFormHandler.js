import {createUser} from "/scripts/api.js";

const tableContainer = document.querySelector('#tableContainer');
const usersTable = document.querySelector('#usersTable');

function createUserForm(isAdmin) {
    const html = document.createElement('div');
    html.setAttribute("id", "addForm");
    html.setAttribute("class", "table__container");
    html.innerHTML = `<form id="user-form" class="user-form">
                        <div class="mb-3 user-form__content">
                            <label for="firstNameAdd" class="form-label user-form__label">First name</label>
                            <input type="text" class="form-control user-form__input" id="firstNameAdd" name="firstNameAdd">
                        </div>
                        <div class="mb-3 user-form__content">
                            <label for="lastNameAdd" class="form-label user-form__label">Last name</label>
                            <input type="text" class="form-control user-form__input" id="lastNameAdd" name="lastNameAdd">
                        </div>
                        <div class="mb-3 user-form__content">
                            <label for="ageAdd" class="form-label user-form__label">Age</label>
                            <input type="number" class="form-control user-form__input" id="ageAdd" name="ageAdd">
                        </div>
                        <div class="mb-3 user-form__content">
                            <label for="emailAdd" class="form-label user-form__label">Email</label>
                            <input type="email" class="form-control user-form__input" id="emailAdd" name="emailAdd">
                        </div>
                        <div class="mb-3 user-form__content">
                            <label for="passwordAdd" class="form-label user-form__label">Password</label>
                            <input type="password" class="form-control user-form__input" id="passwordAdd" name="passwordAdd">
                        </div>
                        <div class="mb-3">
                            <label for="roleAdd" class="form-label user-form__label">Role</label>
                            <select class="form-select" id="roleAdd" multiple name="roleAdd">
                                <option selected value="ROLE_ADMIN">ADMIN</option>
                                <option value="ROLE_USER">USER</option>
                            </select>
                        </div>
                        <button id="addUser" type="submit" class="btn btn-success user-form__button">Add new user</button>
                    </form>`
    const form = html.querySelector("#user-form");
   form.addEventListener("submit", event => {
        event.preventDefault();
        createUser(formHandler(form), isAdmin)
            .then(res => res.json())
            .then(res => console.log(res))
            .finally(() => {
                form.reset();
                usersTable.click();
            })
    })
    return html;
}

function showUserForm(isAdmin) {
    tableContainer.innerHTML = '';
    tableContainer.insertAdjacentElement('beforeend', createUserForm(isAdmin));
}

function formHandler(form) {
    const object = {};
    const roles = [];
    const{firstNameAdd, lastNameAdd, ageAdd, emailAdd, passwordAdd, roleAdd} = form.elements;
    if(roleAdd.options[0].selected == true) {
        roles.push({
            "id": 1,
            "role": "ROLE_ADMIN"
        })
    }
    if(roleAdd.options[1].selected == true) {
        roles.push({
            "id": 2,
            "role": "ROLE_USER"
        })
    }
    object.firstName = firstNameAdd.value;
    object.lastName = lastNameAdd.value;
    object.age = ageAdd.value;
    object.email = emailAdd.value;
    object.password = passwordAdd.value.trim();
    object.roles = roles;
    return object;
}

// <=================== EXPORT ===========================>
export {showUserForm};