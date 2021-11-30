const body = document.querySelector('#body');
const adminRole = document.querySelector('#admin');
const userRole = document.querySelector('#user');
const headerTitle = document.querySelector('.content__header');
const contentDescription = document.querySelector('#contentDescription');
const panelNav = document.querySelector('#panelNav');
const usersTable = document.querySelector('#usersTable');
const newUser = document.querySelector('#newUser');
const addUserButton = document.querySelector('#addUser');
const editButtons = document.querySelectorAll('.button__edit');
const deleteButtons = document.querySelectorAll('.button__delete')
const closeCrossButtons = document.querySelectorAll('.close__button');

const table = document.querySelector('#table');
const addForm = document.querySelector('#addForm');
const editForm = document.querySelector('#editForm');
const deleteForm = document.querySelector('#deleteForm');
const popups = document.querySelectorAll('.popup');

const invisible = "invisible";
const block = "block";
const flex = "flex";
const active = "active";


// =========== Переключение контента на вкладках (таблица/форма)
newUser.addEventListener('click', event => {
    table.classList.add(invisible);
    usersTable.classList.remove(active);
    newUser.classList.add(active);
    addForm.classList.remove(invisible);
});

usersTable.addEventListener('click', event => {
    table.classList.remove(invisible);
    usersTable.classList.add(active);
    newUser.classList.remove(active);
    addForm.classList.add(invisible);
})


// =============== Переключение контента на сайдбаре
userRole.addEventListener('click', () => {
    adminRole.classList.remove(active);
    userRole.classList.add(active);
    panelNav.classList.add(invisible);
    headerTitle.textContent = "User information-page";
    contentDescription.textContent = "About user";
})

adminRole.addEventListener('click', () => {
    adminRole.classList.add(active);
    userRole.classList.remove(active);
    panelNav.classList.remove(invisible);
    headerTitle.textContent = "Admin panel";
    contentDescription.textContent = "All users";
})


// ============= Обработка кнопок
editButtons.forEach(el => {
    el.addEventListener('click', event => {
        editForm.classList.remove(invisible);
        body.style.overflow = 'hidden';
    })
})

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

deleteButtons.forEach(button => {
    button.addEventListener('click', () => {
        deleteForm.classList.remove(invisible);
        body.style.overflow = 'hidden';
    })
})







