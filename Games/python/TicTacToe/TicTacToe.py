#!/usr/bin/env python
# coding: utf-8

import random

def display_board(gameboard):
    print('\n'*100)
    horizontal = '-'
    vertical = '|'
    newline = '\n'
    space = ' '
    width = 10
    height = 4
    grid = [3,3]
    board = []
    mid_width = width/2
    mid_height = height/2
    x = 0
    y = 0
    
    if grid[0]*grid[1] != len(gameboard):
        return 'Not enough values for the grid!'
    while y<grid[1]:        
        y+=1
        while height > 0:
            height -=1
            while x<grid[0]:            
                while width > 0 :
                    if(width == mid_width and height == mid_height):
                        board.append(gameboard[(1*x)+(3*(y-1))])
                    elif height >0 :
                        board.append(space)
                    else:
                        if(y<grid[1]):
                            board.append(horizontal)
                        else:
                            board.append(space)
                    width-=1
                x+=1
                if(x<grid[0]):
                    board.append(vertical)
                width = 10
            board.append(newline)
            x = 0
        height=4
    
    print("".join(board))


def player_input():
    
    marker = ''
    while marker.upper() not in ('X','O'):
        marker = input('Player 1, Choose either X or O : ')
    return marker

def place_marker(board, marker, position):
    
    board[position] = marker


def win_check(board, mark):
    win_comb = [[0,1,2],[3,4,5],[6,7,8],[0,3,6],[1,4,7],[2,5,8],[0,4,8],[2,4,6]]
    
    check = [i for i,marker in enumerate(board) if marker == mark]
    #print(check)
    index = 0
    win = True
    for ind in win_comb:
        #print(ind)
        win = True
        for i in ind:
            #print(i)
            win*=(i in check)
        if win:
            return True
    return False


def choose_first():
    return (random.randint(1,2))


def space_check(board, position):
    
    return board[position] != 'X' and board[position] != 'O'


def full_board_check(board):
    
    return len([x for x in board if x== 'X' or x== 'O']) == len(board)


def player_choice(board):
    
    valid_value = False
    
    while not valid_value:
        mark_index = input('Enter position from 1-9 : ')
        if (mark_index) not in (str(list(range(1,10,1)))):
            continue
        if(space_check(board,int(mark_index)-1)):
            return mark_index
        else:
            return -1


def replay():
    valid_entry = False
    while not valid_entry:
        choice = input('Play again? Yes/No : ')
        if choice.lower() == 'yes':
            return True
        elif choice.lower() == 'no':
            return False
        
print('Welcome to Tic Tac Toe!')

while True:
    # Set the game up here
    #pass
    #name_1 = input('Player 1 name :')
    #name_2 = input('Player 2 name :')
    
    player_1 = player_input().upper()
    player_2 = ''
    
    grid_required = input('Grid number on ? Yes/No : ')
    
    if (grid_required.upper() == 'YES'):
        game_board = ['1','2','3','4','5','6','7','8','9']
    else:
        game_board = [' ']*9
    
    if player_1 == 'X':
        player_2 = 'O'
    else:
        player_2= 'X'
    
    d = {1:player_1,2:player_2}
    
    display_board(game_board)
    
    first_turn = choose_first()
    
    if first_turn == 1:
        second_turn = 2
    else:
        second_turn = 1
        
    print(f'Player {first_turn} to play first.')
    
    while True:
        
        valid_value_1 = False
        valid_value_2 = False
        
        while not valid_value_1:
            position = int(player_choice(game_board))-1
            if(position >= 0):
                place_marker(game_board,d[first_turn],position)
                #print(game_board)
                display_board(game_board)
                valid_value_1 = True
        if(win_check(game_board,d[first_turn])):
            print(f'Player {first_turn} won!')
            break
        if(full_board_check(game_board))        :
            print('Game Over!')
            break
        while not valid_value_2:
            position = int(player_choice(game_board))-1
            if(position >= 0):
                place_marker(game_board,d[second_turn],position)
                display_board(game_board)
                valid_value_2 = True 
        if(win_check(game_board,d[second_turn])):
            print(f'Player {second_turn} won!')
            break
        if(full_board_check(game_board))        :
            print('Game Over!')
            break      

    if not replay():
        break

