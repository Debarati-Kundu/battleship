// Copyright 2012 Google Inc. All Rights Reserved.

/**
 * @fileoverview
 * Provides methods for the TicTacToe sample UI and interaction with the
 * TicTacToe API.
 *
 * @author danielholevoet@google.com (Dan Holevoet)
 */

/** google global namespace for Google projects. */
var google = google || {};

function IsNumeric(n) { return /^-?[\d.]+(?:e-?\d+)?$/.test(n); } 
function pow10(n) {if (n == 0) return 1; else return 10;}

/** devrel namespace for Google Developer Relations projects. */
google.devrel = google.devrel || {};

/** samples namespace for DevRel sample code. */
google.devrel.samples = google.devrel.samples || {};

/** TicTacToe namespace for this sample. */
google.devrel.samples.ttt = google.devrel.samples.ttt || {};

/**
 * Status for an unfinished game.
 * @type {number}
 */
google.devrel.samples.ttt.NOT_DONE = 0;

/**
 * Status for a victory.
 * @type {number}
 */
google.devrel.samples.ttt.WON = 1;

/**
 * Status for a loss.
 * @type {number}
 */
google.devrel.samples.ttt.LOST = 2;

/**
 * Status for a tie.
 * @type {number}
 */
google.devrel.samples.ttt.TIE = 3;

/**
 * Strings for each numerical status.
 * @type {Array.number}
 */
google.devrel.samples.ttt.STATUS_STRINGS = [
    'NOT_DONE',
    'WON',
    'LOST',
    'TIE'
];

// Variables pertaining to ship arrangement by the user
var v0 = 0, v1 = 0, v2 = 0, v3 = 0, v4 = 0;
var p0 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0;
var myboard1 = new Array();
for (var i = 0; i < 100; i++)
	myboard1[i] = '-';
var shiplocation = new Array();
for (var i = 0; i < 5; i++)  { 
	shiplocation[i] = new Array(); 
	for (var j = 0; j < 5; j++)  {
		shiplocation[i][j] = -1;
	}
}
var numSunk = 0;
var numLost = 0;
var gameID;
var status1 = google.devrel.samples.ttt.NOT_DONE;
/**
 * Whether or not the user is signed in.
 * @type {boolean}
 */
google.devrel.samples.ttt.signedIn = false;

/**
 * Whether or not the game is waiting for a user's move.
 * @type {boolean}
 */
google.devrel.samples.ttt.waitingForMove = true;

/**
 * Signs the user out.
 */
google.devrel.samples.ttt.signout = function() {
  document.getElementById('signinButtonContainer').classList.add('visible');
  document.getElementById('signedInStatus').classList.remove('visible');
  google.devrel.samples.ttt.setBoardEnablement(false);
  google.devrel.samples.ttt.signedIn = false;
}

/**
 * Handles a square click.
 * @param {MouseEvent} e Mouse click event.
 */
google.devrel.samples.ttt.clickSquare = function(e) {
  if (google.devrel.samples.ttt.waitingForMove) {
    var button = e.target;
    button.innerHTML = 'X';
    button.removeEventListener('click', google.devrel.samples.ttt.clickSquare);
    google.devrel.samples.ttt.waitingForMove = false;

    var boardString = google.devrel.samples.ttt.getBoardString();
//    console.log(boardString);
    var status = google.devrel.samples.ttt.checkForVictory(boardString);
    
    if (status == google.devrel.samples.ttt.NOT_DONE) {
 //   	console.log("***");
      google.devrel.samples.ttt.getComputerMove(boardString);
    } else {
 //   	console.log("***&");
 //     google.devrel.samples.ttt.handleFinish(status);
    	google.devrel.samples.ttt.getComputerMove(boardString);
    }
  }
};

/**
 * Resets the game board.
 */
google.devrel.samples.ttt.resetGame = function() {
	
	google.devrel.samples.ttt.boardCreate();
	document.getElementById('moves').innerHTML = '';
	document.getElementById('sinking').innerHTML = '';
	document.getElementById('lost').innerHTML = '';
	
	var shiporig = document.querySelector("#shiparrange");
	var temp = shiporig.innerHTML;
	shiporig.innerHTML = '';
	shiporig.innerHTML = temp;
	
	shiporig.innerHTML = '';
	shiporig.innerHTML += '<table><tr>';
	shiporig.innerHTML += '<td class="noTop noLeft noRight noBottom"><div><img src="/images/AC.gif"  id="Aircraft carrier" class="draggable"/></div> </td>';
	shiporig.innerHTML += '<td class="noTop noLeft noRight noBottom"><div><img src="/images/BS.gif"  id="Battleship" class="draggable1"/></div> </td>';
	shiporig.innerHTML += '<td class="noTop noLeft noRight noBottom"><div><img src="/images/Dest.gif"  id="Destroyer" class="draggable2"/></div> </td>';
	shiporig.innerHTML += '<td class="noTop noLeft noRight noBottom"><div><img src="/images/Sub.gif"  id="Submarine" class="draggable3"/></div> </td>';
	shiporig.innerHTML += '<td class="noTop noLeft noRight noBottom"><div><img src="/images/Boat.gif"  id="Patrol Boat" class="draggable4"/></div> </td>';
	shiporig.innerHTML += '</tr></table>'; 
	
	console.log(shiporig.innerHTML); 

  var buttons = document.querySelectorAll('td');
  for (var i = 0; i < buttons.length; i++) {
    var button = buttons[i];
    button.removeEventListener('click', google.devrel.samples.ttt.clickSquare);
    button.addEventListener('click', google.devrel.samples.ttt.clickSquare);
    button.innerHTML = '-';
    button.style.color = 'black';
  }
//  document.getElementById('victory').innerHTML = ''; 
  google.devrel.samples.ttt.waitingForMove = true;
  
};

/**
 * Gets whether hit or miss
 */
google.devrel.samples.ttt.getComputerMove = function(boardString) {
	gapi.client.tictactoe.board.gethit({'state': boardString}).execute(function(resp) {
//		console.log(resp.sunk);
//		console.log(resp.allSunk);
		google.devrel.samples.ttt.setBoardFilling(resp.state);
	//	var prevCount = resp.sinkCount;
//		console.log(resp.sinkDiff);
		if (resp.sinkDiff){
		
			var sinkarr = resp.sunk;
			var sinking = document.getElementById('sinking');
			sinking.innerHTML = '';
			if(sinkarr[0]) {
				sinking.innerHTML += "You sunk Aircraft Carrier!<br>";
			} 
			if(sinkarr[1]) {
				sinking.innerHTML += "You sunk Battleship!<br>";
			} 
			if(sinkarr[2]) {
				sinking.innerHTML += "You sunk Destroyer!<br>";
			} 
			if(sinkarr[3]) {
				sinking.innerHTML += "You sunk Submarine!<br>";
			} 	
			if(sinkarr[4]) {
				sinking.innerHTML += "You sunk Patrol Boat!<br>";
			}		
			
		}
	
		if(resp.allSunk == false) {  // To be changed later to handle game ending cases.			
			google.devrel.samples.ttt.waitingForMove = true;
		}
		else {
			var sinking = document.getElementById('sinking');
			sinking.innerHTML = 'You sunk all ships!<br>';
			sinking.style.color = "red";
			var statearr = resp.state;
			var count = 0;
			for(var i = 0; i < statearr.length; i++) {
				if (statearr.charAt(i) != '-') {
					count++;
				}
			}
//			console.log(statearr);
//			console.log(statearr.length);
//			console.log(count);
			var moves = document.getElementById('moves');
			moves.innerHTML = 'You needed ' + count + ' moves!<br>';
			moves.style.color = "green";
			// Print how many moves needed
		}
	});
};

/**
 * Gets the computer's move.
 * @param {string} boardString Current state of the board.
 
google.devrel.samples.ttt.getComputerMove = function(boardString) {
  gapi.client.tictactoe.board.getmove({'state': boardString}).execute(
      function(resp) {
    google.devrel.samples.ttt.setBoardFilling(resp.state);
    var status = google.devrel.samples.ttt.checkForVictory(resp.state);
    if (status != google.devrel.samples.ttt.NOT_DONE) {
      google.devrel.samples.ttt.handleFinish(status);
    } else {
      google.devrel.samples.ttt.waitingForMove = true;
    }
  });
}; */

/**
 * Sends the result of the game to the server.
 * @param {number} status Result of the game.
 */
google.devrel.samples.ttt.sendResultToServer = function(status) {
  gapi.client.tictactoe.scores.insert({'outcome':
      google.devrel.samples.ttt.STATUS_STRINGS[status]}).execute(
      function(resp) {
    google.devrel.samples.ttt.queryScores();
  });
};

google.devrel.samples.ttt.gameCreate = function() {
	gapi.client.tictactoe.board.gamecreate().execute(function(resp) {
		gameID = resp.state;
//		console.log(gameID);
	});
}

google.devrel.samples.ttt.boardCreate = function() {
	gapi.client.tictactoe.board.create().execute(function(resp) {
		
	});
}
/*
google.devrel.samples.ttt.boardChannel = function() {
	gapi.client.tictactoe.board.getchannel().execute(function(resp) {
		responseToken(resp.state);		
	});
}

responseToken = function(token) {
//	console.log(token);
	var channel = new goog.appengine.Channel(token);
//	console.log(channel);
	var socket = channel.open();
//	console.log(socket);
	socket.onopen = onSocketOpen;
//	socket.onmessage = onSocketMessage;
	socket.onerror = onSocketError;
	socket.onclose = onSocketClose;
}

onSocketError = function(error){
	alert("Error is <br/>"+error.description+" <br /> and HTML code"+error.code);
};

onSocketOpen = function() {
	// socket opened
};

onSocketClose = function() {
	alert("Socket Connection closed");
};

sendMessage = function(path, opt_param) {
	  path += '?g=' + state.game_key;
	  if (opt_param) {
	    path += '&' + opt_param;
	  }
	  var xhr = new XMLHttpRequest();
	  xhr.open('POST', path, true);
	  xhr.send();
	};
*/
/**
 * Queries for results of previous games.
 */

google.devrel.samples.ttt.queryScores = function() {
  gapi.client.tictactoe.scores.list().execute(function(resp) {
    var history = document.getElementById('gameHistory');
    history.innerHTML = '';
    if (resp.items) {
      for (var i = 0; i < resp.items.length; i++) {
        var score = document.createElement('li');
        score.innerHTML = resp.items[i].outcome;
        history.appendChild(score);
      }
    }
  });
};     

/**
 * Shows or hides the board and game elements.
 * @param {boolean} state Whether to show or hide the board elements.
 */
google.devrel.samples.ttt.setBoardEnablement = function(state) {
  if (!state) {
    document.getElementById('board').classList.add('hidden');
    document.getElementById('gameHistoryWrapper').classList.add('hidden');
    document.getElementById('warning').classList.remove('hidden');
  } else {
    document.getElementById('board').classList.remove('hidden');
    document.getElementById('gameHistoryWrapper').classList.remove('hidden');
    document.getElementById('warning').classList.add('hidden');
  }
};

/**
 * Sets the filling of the squares of the board.
 * @param {string} boardString Current state of the board.
 */
google.devrel.samples.ttt.setBoardFilling = function(boardString) {
  var buttons = document.querySelectorAll('td');
  for (var i = 0; i < buttons.length; i++) {
    var button = buttons[i];
//    button.innerHTML = "<font color='black'>" + "X" + "</font>"; //boardString.charAt(i);
    button.innerHTML = boardString.charAt(i);
//    if(boardString.charAt(i) == 'H') button.style.color = "red";
    if((boardString.charAt(i) != 'H') && (boardString.charAt(i) != 'S')) button.style.color = "black"; 
    	else { 
    		button.style.color = "red"; 
    		/*
    		if (boardString.charAt(i) == 'S') {
    			button.innerHTML = "<S>"+ button.innerHTML +"</S>";
    		}*/
    	}
    
  }
};

/**
 * Checks for a victory condition.
 * @param {string} boardString Current state of the board.
 * @return {number} Status code for the victory state.
 */
google.devrel.samples.ttt.checkForVictory = function(boardString) {
  var status = google.devrel.samples.ttt.NOT_DONE;

  // Checks rows and columns.
  for (var i = 0; i < 3; i++) {
    var rowString = google.devrel.samples.ttt.getStringsAtPositions(
        boardString, i*3, (i*3)+1, (i*3)+2);
    status |= google.devrel.samples.ttt.checkSectionVictory(rowString);

    var colString = google.devrel.samples.ttt.getStringsAtPositions(
      boardString, i, i+3, i+6);
    status |= google.devrel.samples.ttt.checkSectionVictory(colString);
  }

  // Check top-left to bottom-right.
  var diagonal = google.devrel.samples.ttt.getStringsAtPositions(boardString,
      0, 4, 8);
  status |= google.devrel.samples.ttt.checkSectionVictory(diagonal);

  // Check top-right to bottom-left.
  diagonal = google.devrel.samples.ttt.getStringsAtPositions(boardString, 2,
      4, 6);
  status |= google.devrel.samples.ttt.checkSectionVictory(diagonal);

  if (status == google.devrel.samples.ttt.NOT_DONE) {
    if (boardString.indexOf('-') == -1) {
      return google.devrel.samples.ttt.TIE;
    }
  }

  return status;
};

/**
 * Checks whether a set of three squares are identical.
 * @param {string} section Set of three squares to check.
 * @return {number} Status code for the victory state.
 */
google.devrel.samples.ttt.checkSectionVictory = function(section) {
  var a = section.charAt(0);
  var b = section.charAt(1);
  var c = section.charAt(2);
  if (a == b && a == c) {
    if (a == 'X') {
      return google.devrel.samples.ttt.WON;
    } else if (a == 'O') {
      return google.devrel.samples.ttt.LOST
    }
  }
  return google.devrel.samples.ttt.NOT_DONE;
};

/**
 * Handles the end of the game.
 * @param {number} status Status code for the victory state.
 */
google.devrel.samples.ttt.handleFinish = function(status) {
  var victory = document.getElementById('victory');
  if (status == google.devrel.samples.ttt.WON) {
    victory.innerHTML = 'You win!';
  } else if (status == google.devrel.samples.ttt.LOST) {
    victory.innerHTML = 'You lost!';
  } else {
    victory.innerHTML = 'You tied!';
  }
  google.devrel.samples.ttt.sendResultToServer(status);
};

/**
 * Gets the current configuration of the user's ships.
 * @return {string} Current state of the board.
 */

google.devrel.samples.ttt.getUserBoardString = function(e) {
	if(!(p0 && p1 && p2 && p3 && p4)) {
		alert("You haven't placed all your ships!");
	} else {
	
	var button = e.target;
	button.removeEventListener('click', google.devrel.samples.ttt.getUserBoardString);

	var userBoardStrings = [];
	for (var i = 0; i < 100; i++) myboard1[i] = '-';
	for (var i = 0; i < 5; i++) {
		var shipcar, shiplen;
		if(i == 0) { shipcar = 'A'; shiplen = 5; }
		if(i == 1) { shipcar = 'B'; shiplen = 4; }
		if(i == 2) { shipcar = 'D'; shiplen = 3; }
		if(i == 3) { shipcar = 'S'; shiplen = 3; }
		if(i == 4) { shipcar = 'P'; shiplen = 2; }
		for (var j = 0; j < shiplen; j++) {
			myboard1[shiplocation[i][j]] = shipcar;
		}
	}
	
	for(var i = 0; i < 100; i++) {
		userBoardStrings.push(myboard1[i]);
	}
	gapi.client.tictactoe.board.getUserShips({'state': userBoardStrings.join(''), 'shipLoc' : shiplocation
		, 'gameID' : gameID}).execute(function(resp) {
//		console.log(userBoardStrings.join(''));
	}); 
	$('.draggable').draggable("disable");
	$('.draggable1').draggable("disable");
	$('.draggable2').draggable("disable");
	$('.draggable2').draggable("disable");
	$('.draggable4').draggable("disable");
	
	alert("Your ships have been successfully placed!"); 
	}
/*	  var boardStrings = [];
	  var buttons = document.querySelectorAll('td');
	  for (var i = 0; i < buttons.length; i++) {
	    boardStrings.push(buttons[i].innerHTML);
	  }
	  return boardStrings.join(''); */
};
	
/**
 * Gets the current representation of the board.
 * @return {string} Current state of the board.
 */
google.devrel.samples.ttt.getBoardString = function() {
  var boardStrings = [];
  var buttons = document.querySelectorAll('td');
  for (var i = 0; i < buttons.length; i++) {
    boardStrings.push(buttons[i].innerHTML);
  }
  return boardStrings.join('');
};

/**
 * Gets the values of the board at the given positions.
 * @param {string} boardString Current state of the board.
 * @param {number} first First element to retrieve.
 * @param {number} second Second element to retrieve.
 * @param {number} third Third element to retrieve.
 */
google.devrel.samples.ttt.getStringsAtPositions = function(boardString, first,
    second, third) {
  return [boardString.charAt(first),
          boardString.charAt(second),
          boardString.charAt(third)].join('');
};

/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 * @param {string} tokenEmail The email parsed from the auth/ID token.
 */
google.devrel.samples.ttt.init = function(apiRoot, tokenEmail) {
//	console.log(tokenEmail);
  // Loads the Tic Tac Toe API asynchronously, and triggers login
  // in the UI when loading has completed.
  var callback = function() {
    google.devrel.samples.ttt.signedIn = true;
    document.getElementById('userLabel').innerHTML = tokenEmail;
    google.devrel.samples.ttt.setBoardEnablement(true);
//    google.devrel.samples.ttt.queryScores();
//    google.devrel.samples.ttt.boardCreate(); // Added by me to initialize the board
//    google.devrel.samples.ttt.boardChannel();
    google.devrel.samples.ttt.gameCreate();
  }
  gapi.client.load('tictactoe', 'v1', callback, apiRoot);

  /*
  var buttons = document.querySelectorAll('td');
  for (var i = 0; i < buttons.length; i++) {
    var button = buttons[i];
//    console.log(button.id);
    if(IsNumeric(button.id))
    	button.addEventListener('click', google.devrel.samples.ttt.clickSquare);
  } */

  var place = document.querySelector('#placedShips');
  place.addEventListener('click', google.devrel.samples.ttt.getUserBoardString);
  
  var reset = document.querySelector('#restartButton');
  reset.addEventListener('click', google.devrel.samples.ttt.resetGame);
};

google.devrel.samples.ttt.clickCell = function(e) {
	if (google.devrel.samples.ttt.waitingForMove) {
		var button = e.target;
		button.removeEventListener('click', google.devrel.samples.ttt.clickCell);
//	    google.devrel.samples.ttt.waitingForMove = false;	    
		var temp = button.id - 100;
//		console.log(temp);
		
		//Sends user's move to server and gets back if it was a hit or miss
		gapi.client.tictactoe.board.getusermove({'state' : temp, 'gameID' : gameID}).execute(function(resp) {
			if(status1 == google.devrel.samples.ttt.NOT_DONE)
				{
					if(resp.state == "MISS") {
						button.innerHTML = 'X';
						button.style.color = 'black';
					} else {
						button.innerHTML = 'X';
						button.style.color = 'red';
					}	
					
					if(resp.gameID != "XXX") {
						numSunk++;
						var sinking = document.getElementById('sinking');
						sinking.innerHTML += resp.gameID + " sunk! :-)";
						sinking.innerHTML += '<br>';				
					}
				}
			if(numSunk == 5)
				{
					sinking.innerHTML += "You won!";
					status1 = google.devrel.samples.ttt.WON;	
					// TODO: To send the results to the server
				}
			if(status1 == google.devrel.samples.ttt.NOT_DONE)
			{				
				var temp = resp.celltarget;
				var target = document.getElementById(temp);			
				target.innerHTML = 'X';
				if (resp.cellstate == "MISS")
					target.style.color = 'black';
				else {
					console.log(resp.cellstate);
					target.innerHTML = '<img src="/images/cross.gif" height="30" width="30">';
				//	target.style.color = 'red'; 
				}
				if(resp.mysunk != "XXX") {
					numLost++;
					var lost = document.getElementById('lost');
					lost.innerHTML += resp.mysunk + " lost! :-(";
					lost.innerHTML += '<br>';				
				}
				
			}
			if(numLost == 5) {
				lost.innerHTML += "You lost"; 
				status1 = google.devrel.samples.ttt.LOST;	
			}
//			target.innerHTML = '<img src="/images/cross.gif" height="35" width="35">';
//			console.log(resp.celltarget);
//			console.log(resp.cellstate);
		});
	}
}


drag_rotate = function() {
    $('.draggable').draggable();
    $('.rotated').draggable();	    
    $('.draggable').click(function() {
        $('.draggable').toggleClass('rotated');
        if(v0 == 0) v0 = 1;
        else v0 = 0;
        console.log(v0);
    }); 
    
    $('.draggable1').draggable(); 
    $('.rotated').draggable();	   
    $('.draggable1').click(function() {
        $(this).toggleClass('rotated');
        if(v1 == 0) v1 = 1;
        else v1 = 0;
        console.log(v1);
    }); 
    
    $('.draggable2').draggable();
    $('.rotated').draggable();	   
    $('.draggable2').click(function() {
        $(this).toggleClass('rotated');
        if(v2 == 0) v2 = 1;
        else v2 = 0;
        console.log(v2);
    }); 
    
    $('.draggable3').draggable(); 
    $('.rotated').draggable();	   
    $('.draggable3').click(function() {
        $(this).toggleClass('rotated');
        if(v3 == 0) v3 = 1;
        else v3 = 0;
        console.log(v3);
    }); 
    
    $('.draggable4').draggable();
    $('.rotated').draggable();	   
    $('.draggable4').click(function() {
        $(this).toggleClass('rotated');
        if(v4 == 0) v4 = 1;
        else v4 = 0;
        console.log(v4);
    }); 
    
    $(".cell").droppable({
        drop: function(event,ui) {
        	var shipname = ui.helper.attr('id');
     //   	ui.helper.attr('src', '');
        	var temp = $(this).attr("id");
 //       	$(this).attr("src",'/images/cross.gif');
    		var row, col;    		
        	if (shipname == "Aircraft carrier") {
        		p0 = 1;
        		row = Math.floor(temp/12);
        		col = (temp%12) - 2;
        			for (var i = 0; i < 5; i++) {
        				var index = 10*row + col + i*pow10(v0);
        				myboard1[index] = 'A';
        				shiplocation[0][i] = index;
        			}   			       		
        	}
        	if (shipname == "Battleship") {
        		p1 = 1;
        			row = Math.floor(temp/12);
        		if (v1 == 0)        		
        			col = (temp%12) - 1; 
        		else col = (temp%12) - 1;  
        			for (var i = 0; i < 4; i++) {
        				var index =10*row + col + i*pow10(v1);
        				myboard1[index] = 'B';
        				shiplocation[1][i] = index;
        		}
        	}
        	if (shipname == "Destroyer") {
        		p2 = 1;
        			row = Math.floor(temp/12);
        			col = (temp%12) - 1; 
            			for (var i = 0; i < 3; i++) {
            				var index = 10*row + col + i*pow10(v2);
            				myboard1[index] = 'D';
            				shiplocation[2][i] = index;
            			}      			
        	}
        	if (shipname == "Submarine") {
        		p3 = 1;
    			row = Math.floor(temp/12);
    			col = (temp%12) - 1; 
        			for (var i = 0; i < 3; i++) {
        				var index = 10*row + col + i*pow10(v3);
        				myboard1[index] = 'S';
        				shiplocation[3][i] = index;
        			}
        	}
        	if (shipname == "Patrol Boat") {
        		p4 = 1;
        		row = Math.floor(temp/12);
        		col = (temp%12);  
        			for (var i = 0; i < 2; i++) {
        				var index = 10*row + col + i*pow10(v4);
        				myboard1[index] = 'P';
        				shiplocation[4][i] = index;
        			}
        	}
        	alert(ui.helper.attr('id') + " placed in " + row + " " + col);
//        	ui.draggable("disable");        	
        }
    });
}