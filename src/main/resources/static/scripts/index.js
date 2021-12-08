import {createUserTable, createAdminTable} from '/scripts/utils.js';
import {updateUser, deleteUser} from '/scripts/api.js';
import {showUserForm} from "/scripts/components/newUserFormHandler.js";
import {editFormHandler} from "/scripts/components/editFormHandler.js";
import {deleteFormHandler} from "/scripts/components/deleteFormHandler.js";

const body = document.querySelector('#body');
const adminRole = document.querySelector('#admin');
const userRole = document.querySelector('#user');
const headerTitle = document.querySelector('.content__header');
const contentDescription = document.querySelector('#contentDescription');
const panelNav = document.querySelector('#panelNav');
const usersTable = document.querySelector('#usersTable');
const newUser = document.querySelector('#newUser');
const closeCrossButtons = document.querySelectorAll('.close__button');

const editForm = document.querySelector("#edit-form");
const deleteForm = document.querySelector('#delete-form');
const popups = document.querySelectorAll('.popup');

const invisible = "invisible";
const active = "active";
const roles = document.querySelectorAll(".navbar__role");
const userId = document.querySelector("#id").textContent;
let isAdmin = false;

roles.forEach(el => {
    if(el.textContent === "ADMIN") {
        isAdmin = true;
    }
})

// ================ Отображение для роли
if(!isAdmin) {
    adminRole.classList.add(invisible);
    userRole.classList.add(active);
    panelNav.classList.add(invisible);
    headerTitle.textContent = "User information-page";
    contentDescription.textContent = "About user";
}

if(isAdmin) {
    createAdminTable(isAdmin);
}

if(!isAdmin) {
    createUserTable(userId);
}

// =========== Переключение контента на вкладках (таблица/форма)
newUser.addEventListener('click', event => {
    usersTable.classList.remove(active);
    contentDescription.textContent = "Add new User";
    newUser.classList.add(active);
    showUserForm(isAdmin);
});

usersTable.addEventListener('click', event => {
    usersTable.classList.add(active);
    contentDescription.textContent = "All users";
    newUser.classList.remove(active);
    createAdminTable(isAdmin)
})

// =============== Переключение контента на сайдбаре
userRole.addEventListener('click', () => {
    createUserTable(userId);
    adminRole.classList.remove(active);
    userRole.classList.add(active);
    panelNav.classList.add(invisible);
    headerTitle.textContent = "User information-page";
    contentDescription.textContent = "About user";
})

adminRole.addEventListener('click', () => {
    createAdminTable(isAdmin);
    adminRole.classList.add(active);
    userRole.classList.remove(active);
    usersTable.classList.add(active);
    newUser.classList.remove(active);
    panelNav.classList.remove(invisible);
    headerTitle.textContent = "Admin panel";
    contentDescription.textContent = "All users";
})

// =============== Закрытие модальных окон
closeCrossButtons.forEach(button => {
    button.addEventListener('click', () => {
        popups.forEach(popup => {
            if(!popup.classList.contains(invisible)) {
                popup.classList.add(invisible);
                body.style.overflow = 'visible';
            }
        })
    })
})

// =============== Обработка формы редактирования
editForm.addEventListener("submit", event => {
    event.preventDefault();
    updateUser(editFormHandler(editForm), isAdmin)
        .then(res => res.json())
        .then(res => console.log(res))
        .finally(() => {
            editForm.reset();
            editForm.querySelector('.close__button').click();
            usersTable.click();
        })
})

// =============== Обработка формы удаления
deleteForm.addEventListener('submit', event => {
    event.preventDefault();
    deleteUser(deleteFormHandler(deleteForm), isAdmin)
    .then(res => res.json())
    .then(res => console.log(res))
        .finally(() => {
            deleteForm.reset();
            deleteForm.querySelector('.close__button').click();
            usersTable.click();
        })
})






