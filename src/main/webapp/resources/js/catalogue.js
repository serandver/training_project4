(function (){
    $('body').on('click', '.edit', function() {

        var columnValues = $(this).parent().siblings().map(function() {
            return $(this).text();
        }).get();

        $("#bookId").text(columnValues[0]);
        $("#editBookTitle").val(columnValues[1]);
        $("#editBookAuthor").val(columnValues[2]);
        $("#editInventoryNumber").val(columnValues[3]);
    });

    // $('#editBookForm').on('click', '#editBook', function() {
    //
    //     var bookToSave = {
    //         bookId: $("#bookId").val(),
    //         bookTitle: $("#editBookTitle").val(),
    //         bookAuthor:$("#editBookAuthor").val(),
    //         inventoryNumber:$("#editInventoryNumber").val()
    //     };
    //     console.log(bookToSave);
    //
    //     $.ajax({
    //         url: '/editbooks',
    //         type: 'POST',
    //         data: bookToSave,
    //         success: function (rp) {
    //             // $("td").filter(function() {
    //             //     return $(this).text() == rp.id;
    //             // }).closest("tr").replaceWith(
    //             //     "<tr>"+
    //             //     "<td>"+rp.id+"</td>"+
    //             //     "<td>"+rp.title+"</td>"+
    //             //     "<td>"+rp.author+"</td>"+
    //             //     "<td>"+rp.inventoryNumber+"</td>"+
    //             //     "<td><button class=\"btn btn-success btn-sm edit-modal\" data-toggle=\"modal\" data-target=\"#editModal\" contenteditable=\"false\">Edit</button></td>"+
    //             //     "</tr>"
    //             // );
    //         }
    //     });
    // });
})();