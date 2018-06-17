
function monitorarAjax(data) {
    var ajaxStatus = data.status;
    var ajaxLoader = document.getElementById("ajaxLoader");


    switch (ajaxStatus) {
        case "begin":
            ajaxLoader.style.display = 'block';
            break;


        case "complete":
            ajaxLoader.style.display = 'none';
            break;
    }
}