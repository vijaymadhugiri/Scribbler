// Javascript elements business code for the common code

const modalContainer = document.getElementById('modal-container');
const signUpModal = document.getElementById('modal-dialog-signup');
const signInModal = document.getElementById('modal-dialog-signin');
const createModal = document.getElementById('modal-dialog-createpost');

function onSignUpRequested() {
    modalContainer.style.display = 'block';
    signUpModal.style.display = 'block';
}

function onSignUpCloseRequested() {
    modalContainer.style.display = 'none';
    signUpModal.style.display = 'none';
}

function onSignInRequested() {
    modalContainer.style.display = 'block';
    signInModal.style.display = 'block';
}

function onSignInCloseRequested() {
    modalContainer.style.display = 'none';
    signInModal.style.display = 'none';
}

function onCreatePostRequested() {
    modalContainer.style.display = 'block';
    createModal.style.display = 'block';
}

function onCreatePostCloseRequested() {
    modalContainer.style.display = 'none';
    createModal.style.display = 'none';
}