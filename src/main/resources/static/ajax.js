
$(document).ready(function() {
    /** начнёт работу тогда, когда будет готов DOM, за исключением картинок **/
    showAllUsers();
});
function showAllUsers(){
    $.ajax({
        url: "showUsers",
        data: {},
        type: "get",
        dataType: "json",
        success: function(data){
            $.each(data, function(index, value){
                console.log(value)
                $("table").append(
                   '<tr>' +
                    '<th scope="row">' +  value.id + '</th>'+
                     '<td>'  +  value.name  + '</td>' +
                     '<td>'  +  value.lastName  + '</td>' +
                    '<td>'  +  value.age  + '</td>' +
                    '<td>'  +  value.login  + '</td>' +
                    '<td>'  +  value.password  + '</td>' +
                    '<td id="roles"> </td>' +
                    '</tr>'
                );
                $.each(value.roles, function(index, value){
                    $("#roles").append (
                        value.name + ' '
                    )
                })
            })
        }
    })
}
