
$(document).ready(function() {
    showCurrentUser();
});

function showCurrentUser() {
    $.ajax({
        url: "showCurrentUser",
        data: {},
        type: "get",
        dataType: "json",
        success: function(data){
            console.log(data)
            // $("#user").append(
            //     '<tr>' +
            //     '<th scope="row">' +  value.id + '</th>'+
            //     '<td>'  +  value.name  + '</td>' +
            //     '<td>'  +  value.lastName  + '</td>' +
            //     '<td>'  +  value.age  + '</td>' +
            //     '<td>'  +  value.login  + '</td>' +
            //     '<td>'  +  value.password  + '</td>' +
            //     '<td>'  + roles +  '</td>'
            // );
        }
    })
}