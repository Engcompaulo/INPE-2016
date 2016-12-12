
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script src="js/jquery-3.1.1.min.js"></script>
    
    <style type="text/css">
        body{
            background-color:#ffffff;
        }

        .flexContainer{
            display: -moz-box;
            display: -ms-flexbox;

            display: flex;
            flex-flow: row wrap;
            justify-content:center;
            flex-direction:row;
        }

        .flexTimeline{
            display: -webkit-box;
            display: -moz-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display:flex;
            justify-content: space-around;
            width:100%;
            border-top:2px solid #000;
            margin-top:50px;
            flex-direction:column;
            align-items:stretch;
             -webkit-flex-flow: row wrap;
            -webkit-align-items:stretch;
        }

        .timelineItem{
            color:#000;
            font-size:16px;
            margin:auto;
            
        }

        .timelineItem:after{
            /*border-left:2px solid #ffffff;*/
            margin-left:20px;
            text-align:center;
         
        }
        

        .graphItem{
            background-color:#f9f9f9;
            min-height:10px;
            margin-bottom:10px;
        }

        .pink{
            width:19%;
            background-color:deeppink;

        }

        .yellow{
            width:100%;
            background-color:gold;
        }

        .blue{
            width:18%;
            margin-left:15%;
            background-color:royalblue;
        }

        .green{
            width:35%;
            margin-left:36%;
            background-color:greenyellow;  
        }
        /*
        .red{
            width:12%;
            margin-left:80%;
            background-color:orangered;
        }
        */
    </style>
    <script>
        $(document).ready(function(){
  
            var time = new Date();
  
            var startTime = new Date('1/1/2010');
            //window.alert('startTime: '+startTime);
            var endTime = new Date('1/1/2017'); /*Tem que colocar um ano a mais devido do que o normal*/
            //window.alert('endTime '+endTime);
  
            /*var dif = time.getTime() - startTime.getTime();*/
            var dif = endTime.getTime() - startTime.getTime();
            //window.alert('endTime.getTime()'+endTime.getTime());
            //window.alert('dif '+dif);
  
            dif = Math.round(dif / 1000);
            dif = Math.round(dif / 60);
            dif = Math.round(dif / 60);
            dif  = Math.round(dif / 24);
            dif = Math.round(dif / 30);
            dif  = Math.round(dif / 12);
  
            for(var a = 0; a < dif; a++){
                var result = Number(2010 + a);
    
                var parent = $(".flexTimeline");

                parent.append('<div class="timelineItem" >'+result+'</div>');
            }
  
            // $('.timelineItem').css('width':)  
            $(".orange").css("background", "lightblue");
            $(".orange").css("width", "210px");
            
        });
    </script>
</head>
<body>
    <h1>Hello World!</h1>
    <div class="flexContainer">
  
        <div class="graphItem pink"></div>
        <div class="graphItem yellow"></div>
        <div class="graphItem blue"></div>
        <div class="graphItem green"></div>
        <div class="graphItem orange"></div>

        <div class="flexTimeline">

        </div>
    </div>


</body>
</html>
