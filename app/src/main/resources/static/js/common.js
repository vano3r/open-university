General = {};
GroupList = {};

General.showStandardModal = function (id) {
    $('#' + id).bPopup({
        opacity: 0.6,
        positionStyle: 'fixed'
    });
    $('#group-name').val(null);
    $('#group-year').val(null);
    $('#group-type').val(null);
}

GroupList.showGroupModalWithParams = function (groupName, learningStartDate, academicDegree) {
    General.showStandardModal('group-modal');
    $('#group-name').val(groupName);
    $('#group-year').val(learningStartDate);
    $('#group-type').val(academicDegree);
}