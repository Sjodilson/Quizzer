function updateUI(id){
    var url = '/Quizzer/GetGameStatus?id=' + id;
    $.getJSON(url, function(game) {
    
    if(!game.gameStarted){
        
        if(sessionStorage.getItem('sessionCreated') !== 'true') {
            sessionStorage.setItem('sessionCreated', 'true');
            var html =  '<h1>' + game.name + '</h1>' + 
                        '<hr> <p><strong>' + game.description + '</strong></p>' +
                        '<p>Spelet är inte startat. Klicka på starta för att köra igång.</p>' +
                        '<button type="button" onclick="startGame()" class="btn btn-primary">Starta</button>';
            $( ".gameinfo" ).append( $( html ) );
        }
        updateScoreboard(game.players);
    } else if (game.gameStarted){
        
    }

});
}

function updateScoreboard(players){
    for (var i = 0; i < players.length; i++) {
    var player = players[i];
        if($('#' + player.name).length === 0){
        var html =  '<h5>' + player.name + ' - 0 poäng </h5>' +
                '<div id="' + player.name + '" >' + 
                '<div class="progress progress-info">' +
                '<div class="bar" style="width: 1%"></div>' +
                '</div>'
                '</div>';
        $( ".players" ).append( $( html ) );
    }
}
}

function startGame(){
    var url = '/Quizzer/StartGame?id=' + $.cookie("gameId");
    $.getJSON(url, function(game) {
    console.log(game.status);
    updateUI($.cookie("id"));

});
}






