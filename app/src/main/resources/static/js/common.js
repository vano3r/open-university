General = {};
GroupList = {};
DocumentList = {};

General.showStandardModal = function (id) {
    $('#' + id).bPopup({
        opacity: 0.6,
        positionStyle: 'fixed'
    });
    $('#group-name').val(null);
    $('#group-year').val(null);
    $('#group-type').val(null);
}

General.closeStandardModal = function (id) {
    $('#' + id).bPopup().close();
}

GroupList.showGroupModalWithParams = function (groupName, learningStartDate, academicDegree) {
    General.showStandardModal('group-modal');
    $('#group-name').val(groupName);
    $('#group-year').val(learningStartDate);
    $('#group-type').val(academicDegree);
}

DocumentList.showDeleteConfirmModalWithParams = function (actionForm, nameObject, deleteObjectFormName, deleteObjectFormValue) {
    General.showStandardModal('document-confirm-delete-modal');
    $('#delete-form').attr('action', actionForm);
    $('#delete-name-object').text(nameObject);

    let deleteObject = $('#delete-object');
    deleteObject.attr('name', deleteObjectFormName);
    deleteObject.attr('value', deleteObjectFormValue);
}