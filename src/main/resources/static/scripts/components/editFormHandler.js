function editFormHandler(form) {
    const object = {};
    const roles = [];
    const {passwordEdit, passwordOld, idEdit, firstNameEdit, lastNameEdit, ageEdit, emailEdit, roleEdit} = form.elements;
    if(roleEdit.options[0].selected == true) {
        roles.push({
            "id": 1,
            "role": "ROLE_ADMIN"
        })
    }
    if(roleEdit.options[1].selected == true) {
        roles.push({
            "id": 2,
            "role": "ROLE_USER"
        })
    }
    if(passwordEdit.value.trim() == ''){
        object.password = passwordOld.value;
    } else {
        object.password = passwordEdit.value.trim();
    }
    object.id = idEdit.value;
    object.firstName = firstNameEdit.value;
    object.lastName = lastNameEdit.value;
    object.age = ageEdit.value;
    object.email = emailEdit.value;
    object.roles = roles;
    return object;
}

// <================== EXPORT =====================>
export {editFormHandler};