// https://insights.stackoverflow.com/survey/2018/#technology-most-loved-dreaded-and-wanted-languages

    $("#button-query4").click(function(e) {
        //e.preventDefault();
        console.log("Inside Button query");
        $.ajax({
            type: "GET",
            url: "/sectorTrenddata",
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
        var a = document.getElementById('layout4')
        a.innerHTML= ''


       var div = d3.select("body").append("div")
            .attr("class", "tooltip-donut")
            .style("opacity", 0);

       // set the dimensions and margins of the graph
       var margin = {top: 10, right: 30, bottom: 30, left: 60},
           width = (.5)*$("#layout4").parent().width(),//100%;//460 - margin.left - margin.right,
           height = 400 - margin.top - margin.bottom,
           padding = 100;

       // append the svg object to the body of the page
       var svg = d3.select("#layout4")
         .append("svg")
           .attr("width", width + margin.left + margin.right)
           .attr("height", height + margin.top + margin.bottom + 50)
         .append("g")
           .attr("transform",
                 "translate(" + margin.left + "," + margin.top + ")");

      // group the data: I want to draw one line per group
      var sumstat = d3.nest() // nest function allows to group the calculation per level of a factor
        .key(function(d) { return d.sector;})
        .entries(data);

      // Add X axis --> it is a date format
      var x = d3.scaleLinear()
        .domain(d3.extent(data, function(d) { return d.year; }))
        .range([ 0, width ]);
      svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x).ticks(10));

      // Add Y axis
      var y = d3.scaleLinear()
        .domain([0, d3.max(data, function(d) { return +d.value; })])
        .range([ height, 0 ]);
      svg.append("g")
        .call(d3.axisLeft(y));

      // color palette
      var res = sumstat.map(function(d){ return d.key }) // list of group names
      var color = d3.scaleOrdinal()
        .domain(res)
        .range(['#e41a1c','#377eb8','#4daf4a','#984ea3','#ff7f00','#ffff33','#a65628','#f781bf','#999999'])

      // Draw the line
      svg.selectAll(".line")
          .data(sumstat)
          .enter()
          .append("path")
            .attr("fill", "none")
            .attr("stroke", function(d){ return color(d.key) })
            .attr("stroke-width", 2)
            .attr("d", function(d){
              return d3.line()
                .x(function(d) { return x(d.year); })
                .y(function(d) { return y(+d.value); })
                (d.values)
            })
    		.style("opacity",0.4)
    		.on('mouseover', function(d){
    							console.log(d)
    							d3.select(this)
    								.transition()
    								.duration('50')
    								.style("opacity",1)
    								.attr("stroke-width", 5)
    							div.transition()
    								.duration(50)
    								.style("opacity", 1)
    							div.html(d.key)
    								.style("left", (d3.event.pageX + 20) + "px")
    								.style("top", (d3.event.pageY - 25) + "px")
    								.style("position", "absolute")
    								.style("background", "white")
    						})
    		.on("mouseout", function(d) {
    							d3.select(this)
    								.transition()
    								.duration('500')
    								.style("opacity",0.4)
    								.attr("stroke-width", 2)
    							div.transition()
    								.duration(500)
    								.style("opacity", 0)});
    	svg.append("text")
                    .attr("text-anchor", "middle")  // this makes it easy to centre the text as the transform is applied to the anchor
                    .attr("transform", "translate("+ (width/2) +","+(height + (padding/2))+")")  // centre below axis
                    .text("Year");
        svg.append("text")
                    .attr("text-anchor", "middle")  // this makes it easy to centre the text as the transform is applied to the anchor
                    .attr("transform", "translate("+ -40 +","+((height - 50)/2)+")rotate(-90)")  // text is drawn off the screen top left, move down and out and rotate
                    .text("CO2 emission in terawatt-hours (per sector)");



    }

