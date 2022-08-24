
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
                $("table").append(
                   '<tr>' +
                    '<th scope="row">' +  value.id + '</th>'+
                     '<td>'  +  value.name  + '</td>' +
                     '<td>'  +  value.lastName  + '</td>' +
                    '<td>'  +  value.age  + '</td>' +
                    '<td>'  +  value.login  + '</td>' +
                    '<td>'  +  value.password  + '</td>' +
                    '<td id="roles"> </td>' +

                    '<td><a id="modal-663453" href="#modal-container-663453" role="button"' +
                    ' data-whatever = " " class="btn btn-info" data-toggle="modal">Edit</a></td>' +
                    '</tr>'
                );
                $.each(value.roles, function(index, value){
                    $("#roles").append (
                        value.name + ' '
                    )
                })
                $('#modal-663453').attr('data-whatever',JSON.stringify([value.id , value.name]));


            })
        }
    })
}
