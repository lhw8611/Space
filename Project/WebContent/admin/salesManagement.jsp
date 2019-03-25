<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
System.out.println("jsp 로 넘어옴");
HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
map = (HashMap<Integer,Integer>)request.getAttribute("map");
	for(int i=0; i<=12; i++) {
		System.out.println(map.get(i));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#container {
	
}

#main {
	width: 1000px;
	margin: 100px auto;
}
</style>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<jsp:include page="adminSidebar.jsp"></jsp:include>
	<div id="container">
		<div id="main">


			<div id="highcharts-8fbb2288-d8c7-42da-9e62-62235709f25a"></div>
			<script>
				(function() {
					var files = [
							"https://code.highcharts.com/stock/highstock.js",
							"https://code.highcharts.com/highcharts-more.js",
							"https://code.highcharts.com/highcharts-3d.js",
							"https://code.highcharts.com/modules/data.js",
							"https://code.highcharts.com/modules/exporting.js",
							"https://code.highcharts.com/modules/funnel.js",
							"https://code.highcharts.com/modules/annotations.js",
							"https://code.highcharts.com/modules/solid-gauge.js" ], loaded = 0;
					if (typeof window["HighchartsEditor"] === "undefined") {
						window.HighchartsEditor = {
							ondone : [ cl ],
							hasWrapped : false,
							hasLoaded : false
						};
						include(files[0]);
					} else {
						if (window.HighchartsEditor.hasLoaded) {
							cl();
						} else {
							window.HighchartsEditor.ondone.push(cl);
						}
					}
					function isScriptAlreadyIncluded(src) {
						var scripts = document.getElementsByTagName("script");
						for (var i = 0; i < scripts.length; i++) {
							if (scripts[i].hasAttribute("src")) {
								if ((scripts[i].getAttribute("src") || "")
										.indexOf(src) >= 0
										|| (scripts[i].getAttribute("src") === "http://code.highcharts.com/highcharts.js" && src === "https://code.highcharts.com/stock/highstock.js")) {
									return true;
								}
							}
						}
						return false;
					}
					function check() {
						if (loaded === files.length) {
							for (var i = 0; i < window.HighchartsEditor.ondone.length; i++) {
								try {
									window.HighchartsEditor.ondone[i]();
								} catch (e) {
									console.error(e);
								}
							}
							window.HighchartsEditor.hasLoaded = true;
						}
					}
					function include(script) {
						function next() {
							++loaded;
							if (loaded < files.length) {
								include(files[loaded]);
							}
							check();
						}
						if (isScriptAlreadyIncluded(script)) {
							return next();
						}
						var sc = document.createElement("script");
						sc.src = script;
						sc.type = "text/javascript";
						sc.onload = function() {
							next();
						};
						document.head.appendChild(sc);
					}
					function each(a, fn) {
						if (typeof a.forEach !== "undefined") {
							a.forEach(fn);
						} else {
							for (var i = 0; i < a.length; i++) {
								if (fn) {
									fn(a[i]);
								}
							}
						}
					}
					var inc = {}, incl = [];
					each(document.querySelectorAll("script"), function(t) {
						inc[t.src.substr(0, t.src.indexOf("?"))] = 1;
					});
					function cl() {
						if (typeof window["Highcharts"] !== "undefined") {
							Highcharts.setOptions({
								lang : {}
							});
							var options = {
								"title" : {
									"text" : "매출액"
								},
								"subtitle" : {
									"text" : "2019년"
								},
								"exporting" : {},
								"chart" : {
									"panning" : true,
									"pinchType" : "x",
									"alignTicks" : false,
									"width" : null,
									"backgroundColor" : "#eeeeee",
									"height" : 667
								},
								"navigator" : {
									"enabled" : true
								},
								"scrollbar" : {
									"enabled" : true
								},
								"rangeSelector" : {
									"enabled" : true,
									"selected" : 1
								},
								"tooltip" : {
									"split" : true,
									"crosshairs" : true
								},
								"legend" : {
									"enabled" : false
								},
								"plotOptions" : {
									"line" : {
										"marker" : {
											"enabled" : false,
											"radius" : 2
										}
									},
									"spline" : {
										"marker" : {
											"enabled" : false,
											"radius" : 2
										}
									},
									"area" : {
										"marker" : {
											"enabled" : false,
											"radius" : 2
										}
									},
									"areaspline" : {
										"marker" : {
											"enabled" : false,
											"radius" : 2
										}
									},
									"arearange" : {
										"marker" : {
											"enabled" : false,
											"radius" : 2
										}
									},
									"areasplinerange" : {
										"marker" : {
											"enabled" : false,
											"radius" : 2
										}
									},
									"column" : {
										"shadow" : false,
										"borderWidth" : 0
									},
									"columnrange" : {
										"shadow" : false,
										"borderWidth" : 0
									},
									"candlestick" : {
										"shadow" : false,
										"borderWidth" : 0
									},
									"ohlc" : {
										"shadow" : false,
										"borderWidth" : 0
									},
									"series" : {
										"animation" : false,
										"dataLabels" : {}
									}
								},
								"series" : [ {
									"name" : "sales",
									"turboThreshold" : 0,
									"type" : "column",
									"dataGrouping" : {
										"units" : [ [ "week", [ 1 ] ],
												[ "month", [ 1, 2, 3, 4, 6 ] ] ]
									},
									"marker" : {}
								} ],
								"xAxis" : [ {
									"minPadding" : 0,
									"maxPadding" : 0,
									"overscroll" : 0,
									"ordinal" : true,
									"title" : {
										"text" : null,
										"style" : {
											"color" : "#A0A0A3"
										}
									},
									"labels" : {
										"overflow" : "justify",
										"style" : {
											"color" : "#E0E0E3"
										}
									},
									"showLastLabel" : true,
									"gridLineColor" : "#707073",
									"lineColor" : "#707073",
									"minorGridLineColor" : "#505053",
									"tickColor" : "#707073",
									"type" : "datetime",
									"categories" : null,
									"startOnTick" : false,
									"endOnTick" : false,
									"index" : 0,
									"isX" : true
								} ],
								"yAxis" : [ {
									"labels" : {
										"y" : -2,
										"style" : {
											"color" : "#E0E0E3"
										}
									},
									"opposite" : true,
									"showLastLabel" : false,
									"title" : {
										"text" : "매출액",
										"style" : {
											"color" : "#A0A0A3"
										}
									},
									"gridLineColor" : "#707073",
									"lineColor" : "#707073",
									"minorGridLineColor" : "#505053",
									"tickColor" : "#707073",
									"tickWidth" : 1,
									"index" : 0
								} ],
								"isStock" : true,
								"data" : {
									"csv" : "\"DateTime\";\"sales\"\n\
<%-- 									for(var i=1; i>=12; i++) {
										"2019-"+i+"-01\";<%=map.get(i)%>\n\
										
									}
									
									<%
									for(int i=1; i<=12; i++) {
									%>
									"2019-<%=i%>-01\";<%map.get(i);\n\"
									
									
									}
									
									%>
									 --%>
									"2019-01-10\";112\n\
									"2019-02-02\";113\n\
									"2019-03-03\";114\n\
									"2019-04-10\";115\n\
									"2019-05-10\";116\n\
									"2019-06-10\";117\n\
									"2019-07-10\";118\n\
									"2019-08-10\";119\n\
									"2019-09-10\";120\n\
									"2019-10-10\";121\n\
									"2019-11-10\";122\n\
									"2019-12-10\";123",
									"googleSpreadsheetKey" : false,
									"googleSpreadsheetWorksheet" : false
								},
								"colors" : [ "#455a64", "#434348", "#90ed7d",
										"#f7a35c", "#8085e9", "#f15c80",
										"#e4d354", "#2b908f", "#f45b5b",
										"#91e8e1" ],
								"lang" : {},
								"credits" : {}
							};/*
							// Sample of extending options:
							Highcharts.merge(true, options, {
							    chart: {
							        backgroundColor: "#bada55"
							    },
							    plotOptions: {
							        series: {
							            cursor: "pointer",
							            events: {
							                click: function(event) {
							                    alert(this.name + " clicked\n" +
							                          "Alt: " + event.altKey + "\n" +
							                          "Control: " + event.ctrlKey + "\n" +
							                          "Shift: " + event.shiftKey + "\n");
							                }
							            }
							        }
							    }
							});
							 */
							new Highcharts.Chart(
									"highcharts-8fbb2288-d8c7-42da-9e62-62235709f25a",
									options);
						}
					}
				})();
			</script>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>