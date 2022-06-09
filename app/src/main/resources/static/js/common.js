General = {};
GroupList = {};

General.showStandardModal = function (id) {
    $('#' + id).bPopup({
        opacity: 0.6,
        positionStyle: 'fixed'
    });
}

GroupList.showGroupModalWithParams = function (groupName) {
    General.showStandardModal('group-modal');
    $('#group-name').val(groupName);
}