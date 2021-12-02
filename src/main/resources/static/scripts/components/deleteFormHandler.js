function deleteFormHandler(form) {
    const {idDelete} = form.elements;
    return idDelete.value;
}

// <================= EXPORT ===================>
export {deleteFormHandler};