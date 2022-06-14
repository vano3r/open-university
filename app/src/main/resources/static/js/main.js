$(document).ready(function() {
    let labels = $('.labels').children().toArray();

    for (let label of labels) {
        let randomColor = Math.floor(Math.random() * 16777215).toString(16);
        $(label).css('background-color','#' + randomColor);
    }
});