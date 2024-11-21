var Category = (function () {

    // Private variables
    var foo = 'bar';
    var URL = "/adminportal/category/sortabledatatable";

    // Constructor
    var Category = function () {
    };

    // Private functions
    function showMeSomething() {
        console.log("Show me something!!!!!");
    }

    function getUrl() {
        return URL;
    }

    // Public functions
    Category.prototype = {

        showMeSomething: function() {
            console.log("Show me something!!!!!");
        },

        updateCategoryOrderToServer: function () {

            var data = [];

            $('tr.row1').each(function(index,element) {
                data.push({
                    categoryId: $(this).attr('data-id'),
                    order: index+1,
                    title: $(this).attr('data-title')

                });
            });

            console.log(data);

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: getUrl(),
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (data) {
                    if (response.status == "success") {
                        console.log(response);
                    } else {
                        console.log(response);
                    }
                },
                error: function (e) {
                    console.log(e);
                }
            });
        }

    };

    return Category;

}());
