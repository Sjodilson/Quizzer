function getGameStatus(id){
    var url = '/Quizzer/GetGameStatus?id=' + id;
    $.getJSON(url, function(game) {
    
    if(!game.gameStarted){
        var html = '<h1>' + game.name + '</h1>' + 
                    '<hr> <p><strong>' + game.description + '</strong></p>' +
                    '<p>Spelet är inte startat. Klicka på starta för att köra igång.</p>' +
                    '<button type="button" class="btn btn-primary">Starta</button>';
        $( ".gameinfo" ).append( $( html ) );
        updateScoreboard(game.players);
    } else{
        
    }

});
}

function updateScoreboard(players){
    for (var i = 0; i < players.length; i++) {
    var game = players[i];
    var html = '<div class=clickable><div class="row-fluid"><div class="span2"><img src="' + game.img + '" class="img-polaroid" style="margin:5px 0px 15px;" alt=""></div><div class="span10">'  +            
                '<h3>' + game.name + '</h3>' +
                '<p>' + game.description + '</p>' +
                '<a href="/Quizzer/game.html?id=' + game.id + '"> </a>' +
                '</div></div><hr></div></div> ';
    $( ".players" ).append( $( html ) );
    
    $(".clickable").click(function(){
         window.location=$(this).find("a").attr("href");
         return false;
    });

}
}






