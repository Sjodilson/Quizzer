function getGames(){
    $.getJSON('/Quizzer/GetGames', function(myData) {
    for (var i = 0; i < myData.length; i++) {
    var game = myData[i];
    var html = '<div class=clickable><div class="row-fluid"><div class="span2"><img src="' + game.img + '" class="img-polaroid" style="margin:5px 0px 15px;" alt=""></div><div class="span10">'  +            
                '<h3>' + game.name + '</h3>' +
                '<p>' + game.description + '</p>' +
                '<a href="/Quizzer/game.html?id=' + game.id + '"> </a>' +
                '</div></div><hr></div></div> ';
    $( ".games" ).append( $( html ) );
    
    $(".clickable").click(function(){
         window.location=$(this).find("a").attr("href");
         return false;
    });

}
});
}



