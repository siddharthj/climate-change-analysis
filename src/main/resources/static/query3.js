// https://insights.stackoverflow.com/survey/2018/#technology-most-loved-dreaded-and-wanted-languages

    $("#button-query3").click(function(e) {
        //e.preventDefault();
        console.log("Inside Button query");
        $.ajax({
            type: "GET",
            url: "/emissionTrendData",
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
        var a = document.getElementById('layout3')
        a.innerHTML= ''

      // set the dimensions and margins of the graph
                 var margin = {top: 10, right: 30, bottom: 30, left: 60},
                     width = (.5)*$("#layout3").parent().width(),//100%;//460 - margin.left - margin.right,
                     height = 400 - margin.top - margin.bottom,
                     padding = 100;

      // append the svg object to the body of the page
      var svg = d3.select("#layout3")
        .append("svg")
          .attr("width", width + margin.left + margin.right)
          .attr("height", height + margin.top + margin.bottom)
        .append("g")
          .attr("transform",
                "translate(" + margin.left + "," + margin.top + ")");

         // Add X axis --> it is a date format
         var x = d3.scaleLinear()
                 .domain(d3.extent(data, function(d) { return d.year; }))
                 .range([ 0, width ]);
               svg.append("g")
                 .attr("transform", "translate(0," + height + ")")
                 .call(d3.axisBottom(x).ticks(10));

          // Add Y axis
          var y = d3.scaleLinear()
            .domain([20, d3.max(data, function(d) { return d.percentageContribution; })])
            .range([ height, 0 ]);
          svg.append("g")
            .call(d3.axisLeft(y));

          // Add the line
          svg.append("path")
            .datum(data)
            .attr("fill", "none")
            .attr("stroke", "steelblue")
            .attr("stroke-width", 2)
            .attr("d", d3.line()
              .x(function(d) { return x(d.year) })
              .y(function(d) { return y(d.percentageContribution) })
              )
svg.append("text")
                    .attr("text-anchor", "middle")  // this makes it easy to centre the text as the transform is applied to the anchor
                    .attr("transform", "translate("+ (width/2) +","+(height + (padding/2))+")")  // centre below axis
                    .text("Year");
        svg.append("text")
                    .attr("text-anchor", "middle")  // this makes it easy to centre the text as the transform is applied to the anchor
                    .attr("transform", "translate("+ -40 +","+((height - 50)/2)+")rotate(-90)")  // text is drawn off the screen top left, move down and out and rotate
                    .text("Percentage contribution of  top 3 emitters");

    }

