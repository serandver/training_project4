(function (){
    $('td button').on('click', function() {
        var orderId = $(this).parent().parent().siblings().html();
        var command = "confirmOrder";
        var req = {};
        req.orderId = orderId;
        req.command = command;

        $.ajax({
            url: '/controller',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(req),
            contentType: 'application/json'
        });
    });


})();