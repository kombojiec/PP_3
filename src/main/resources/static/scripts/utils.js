import {getUsersList, getUserInfo} from '/scripts/api.js'

const invisible = "invisible";
const body = document.querySelector('#body');

const tableContainer = document.querySelector('#tableContainer');
const editFormContainer = document.querySelector('#editForm');
const editForm = document.querySelector('#edit-form');
const deleteFormContainer = document.querySelector('#deleteForm');
const deleteForm = document.querySelector('#delete-form');

function createAdminTable() {
    const table = document.createElement('table');
    table.setAttribute('class','table table-striped content-table');
    table.setAttribute('id', 'table');
    table.insertAdjacentHTML('afterbegin',`<thead>
                        <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        </tr>
                    <thead>
                    <tbody id="tbody">
                    </tbody>`)
    tableContainer.innerHTML = '';
    tableContainer.insertAdjacentElement('beforeend', table);
    renderAdminList(getUsersList())
}

function createUserTable(id) {
    const table = document.createElement('table');
    table.setAttribute('class','table table-striped content-table');
    table.setAttribute('id', 'table');
    table.insertAdjacentHTML('afterbegin',`<thead>
                        <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Role</th>
                        </tr>
                    <thead>
                    <tbody id="tbody">
                    </tbody>`)
    tableContainer.innerHTML = '';
    tableContainer.insertAdjacentElement('beforeend', table);
    renderUserList(getUserInfo(id));
}

function createAdminRow(el){
    const html = document.createElement('tr');
    html.innerHTML = `<td>${el.id}</td>
    <td>${el.firstName}</td>
        <td>${el.lastName}</td>
        <td>${el.age}</td>
        <td>${el.email}</td>        
        <td>${roleConcatenate(el.roles)}</td>
        <td>
            <button type="button" class="btn btn-info button__edit">
                Edit
            </button>
        </td>
        <td>
            <button type="button" class="btn btn-danger button__delete">
                Delete
            </button>
        </td>`
    html.querySelector('.button__edit')
        .addEventListener("click", () => {
            fillEditForm(editForm, el, editFormContainer);
        })
    html.querySelector('.button__delete')
        .addEventListener("click", () => {
            fillDeleteForm(deleteForm, el, deleteFormContainer);
        })
    return html;
}

function createUserRow (el) {
    const html = document.createElement('tr');
    html.innerHTML = `<td>${el.id}</td>
    <td>${el.firstName}</td>
        <td>${el.lastName}</td>
        <td>${el.age}</td>
        <td>${el.email}</td>        
        <td>${roleConcatenate(el.roles)}</td>`
    return html;
}

async function renderAdminList(fetch) {
    const tbody = document.querySelector('#tbody');
    await fetch
        .then(res => res.json())
        .then(res => {
            res.forEach(el => {
                tbody.insertAdjacentElement('beforeend', createAdminRow(el));
            })
        });
}

function renderUserList(fetch) {
    const tbody = document.querySelector('#tbody');
     fetch
        .then(res => res.json())
        .then(res => {
            tbody.insertAdjacentElement('beforeend', createUserRow(res));
        });
}

function roleConcatenate(arr) {
    let string = '';
    arr.forEach(el => string = string +  el.role?.substr(5) + '\n');
    return string;
}

function fillEditForm(form, user, container) {
    const {idEdit, passwordOld, firstNameEdit, lastNameEdit, ageEdit, emailEdit, roleEdit} = form.elements;
    passwordOld.value = user.password;
    idEdit.value = user.id;
    firstNameEdit.value = user.firstName;
    lastNameEdit.value = user.lastName;
    ageEdit.value = user.age;
    emailEdit.value = user.email;
    roleEdit.value = user.roles;

    user.roles.forEach(role => {
        role.role === roleEdit.options[0].value?
            roleEdit.options[0].selected = true: false;
        role.role === roleEdit.options[1].value?
            roleEdit.options[1].selected = true: false;
    })
    showForm(container);
}

function fillDeleteForm(form, user, container) {
    const {idDelete, firstNameDelete, lastNameDelete, ageDelete, emailDelete, roleDelete} = form.elements;
    idDelete.value = user.id;
    firstNameDelete.value = user.firstName;
    lastNameDelete.value = user.lastName;
    ageDelete.value = user.age;
    emailDelete.value = user.email;
    roleDelete.value = user.roles;

    user.roles.forEach(role => {
        role.role === roleDelete.options[0].value?
            roleDelete.options[0].selected = true: false;
        role.role === roleDelete.options[1].value?
            roleDelete.options[1].selected = true: false;
    })
    showForm(container);
}

function showForm(container) {
    container.classList.remove(invisible);
    body.style.overflow = 'hidden';
}

//  <=============== EXPORT ===============>
export {createUserTable, createAdminTable};



