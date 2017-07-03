(function (){
    $('body').on('click', 'button #edit-modal', function() {

        var columnValues = $(this).parent().siblings().map(function() {
            return $(this).text();
        }).get();

        $("#bookId").text(columnValues[0]);
        $("#editBookTitle").val(columnValues[1]);
        $("#editBookAuthor").val(columnValues[2]);
        $("#editInventoryNumber").val(columnValues[3]);
    });

    $('#editModal').on('click', '#editBook', function() {

        var bookToSave = {
            contactId: $("#bookId").text(),
            firstName: $("#editBookTitle").val(),
            lastName:$("#editBookAuthor").val(),
            patronymic:$("#editInventoryNumber").val()
        };

        $.ajax({
            url: '/controller',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(bookToSave),
            contentType: 'application/json',
            success: function (rp) {
                $("td").filter(function() {
                    return $(this).text() == rp.id;
                }).closest("tr").replaceWith("<tr>"+
                    "<td>"+rp.id+"</td>"+
                    "<td>"+rp.title+"</td>"+
                    "<td>"+rp.author+"</td>"+
                    "<td>"+rp.inventoryNumber+"</td>"+
                    "<td><button class=\"btn btn-success btn-sm edit-modal\" data-toggle=\"modal\" data-target=\"#editModal\" contenteditable=\"false\">Edit</button></td>"+
                    "</tr>");
            }
        });
    });
})();