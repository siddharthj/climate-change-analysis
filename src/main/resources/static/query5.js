// https://insights.stackoverflow.com/survey/2018/#technology-most-loved-dreaded-and-wanted-languages

    $("#button-query5").click(function(e) {
        //e.preventDefault();
        console.log("Inside Button query");
        $.ajax({
            type: "GET",
            url: "/dbdata",
            dataType:'json',
            data: {
                name: "testing my beautiful code :-*"
            },
            success: function(result) {
                //alert('testing my beautiful code :-*');
                printChart(result)
            },
            error: function(result) {
                //alert('error');
            }
        });
    });

    function printChart(data) {
        var a = document.getElementById('layout5')
        var table = '<table class="table"> <tr> <th> Table Name </th> <th> Number of records</th></tr>'
        data.forEach(function (item, index) {
          table+='<tr><td>' + item.tableName + '</td><td>' + item.rowCount + '</td></tr>';
        });
        var total = 0;
        data.forEach(function (item, index) {
                  total+=item.rowCount;
                });
        table+='<tr><td><b>TOTAL</b></td><td>' + total + '</td></tr>';
        table += '</table>';
        a.innerHTML= table;
    }

