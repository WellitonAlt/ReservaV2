
function monitorarAjax(data) {
    var ajaxStatus = data.status;
    var ajaxLoader = document.getElementById("ajaxLoader");
    
    var input_selector = 'input[type=text], input[type=password]';


    switch (ajaxStatus) {
        case "begin":
            ajaxLoader.style.display = 'block';
            break;


        case "complete":
            ajaxLoader.style.display = 'none';
            break;
    }
}