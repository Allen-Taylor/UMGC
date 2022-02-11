"""
Student: Allen Taylor
Program: Python Command Line Math and Security Related Application Code
Class/Assignment: SDEV 300 6382/Lab 2
Date: 1/22/2022
"""

import string
import datetime
import math
import sys
import secrets

def pw_gen():
    """ Generates a random password.
    Returns:
        A secure password (string).
    """
    valid = ("YES","Y","NO","N")
    yes = ("YES","Y")
    password_string = ""
    print ("o--------------------------o")
    print ("| Generate Secure Password |")
    print ("o--------------------------o")
    pw_length = None
    while pw_length is None:
        try:
            pw_length = int(input("Enter password length: "))
            if pw_length < 1 or pw_length > 64:
                print("Length must be greater than 0 and less than 64.")
                pw_length = None # Loop again
        except ValueError:
            print('Please enter a number.')
    while True:
        upper = str(input("Use of Upper Case? (Yes or No): ")).upper()
        if upper in valid:
            if upper in yes:
                password_string = password_string + string.ascii_uppercase
            break # Exit While Loop for Upper Case
        print("Enter a valid response.")
    while True:
        lower = str(input("Use of Lower Case? (Yes or No): ")).upper()
        if lower in valid:
            if lower in yes:
                password_string = password_string + string.ascii_lowercase
            break # Exit While Loop for Lower Case
        print("Enter a valid response.")
    while True:
        digits = str(input("Use of Numbers? (Yes or No): ")).upper()
        if digits in valid:
            if digits in yes:
                password_string = password_string + string.digits
            break # Exit While Loop for Numbers
        print("Enter a valid response.")
    while True:
        spec_chars = str(input("Use of Special Characters? (Yes or No): ")).upper()
        if spec_chars in valid:
            if spec_chars in yes:
                password_string = password_string + string.punctuation
            break # Exit While Loop for Special Characters
        print("Enter a valid response.")
    if password_string == "":
        return "You have selected NO to all items. Please try again."
    password = ''.join(secrets.choice(password_string) for i in range(pw_length))
    return password

def check_negative(num):
    """ Checks for negative numbers.
    Returns:
        Returns true if number is negative.
    """
    return num < 0

def percentage():
    """ Calculates and Formats a Percentage.
    Returns:
        Returns a percentage.
    """
    print ("o------------------------------------o")
    print ("| Calculate and Format a Percentage. |")
    print ("o------------------------------------o")
    numerator = None
    while numerator is None:
        try:
            numerator = float(input("Enter the numerator: "))
            if check_negative(numerator):
                numerator = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    denominator = None
    while denominator is None:
        try:
            denominator = float(input("Enter the denominator: "))
            if check_negative(denominator):
                denominator = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    dec_points = None
    while dec_points is None:
        try:
            dec_points = int(input("Enter the number of decimal points: "))
            if check_negative(dec_points):
                dec_points = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    try:
        percent = (numerator/denominator)*100
    except ZeroDivisionError:
        return "Error! Cannot divide by zero!"
    else:
        return round(percent, dec_points)

def today_until():
    """ Days between now and July 4, 2025.
    Returns:
        Number of days.
    """
    today = datetime.date.today()
    futdate = datetime.date(2025, 7, 4)
    days = (futdate - today).days
    return days

def law_of_cosines():
    """ Find the third side of the triangle using law of cosines.
    Returns:
        Length of the third side of a triangle.
    """
    print ("o-----------------------------------------------------------o")
    print ("| Find the third side of the triangle using law of cosines. |")
    print ("o-----------------------------------------------------------o")
    side_a = None
    while side_a is None:
        try:
            side_a = float(input("Enter the length of side A: "))
            if check_negative(side_a):
                side_a = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    side_b = None
    while side_b is None:
        try:
            side_b = float(input("Enter the length of side B: "))
            if check_negative(side_b):
                side_b = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    angle_c = None
    while angle_c is None:
        try:
            angle_c = float(input("Enter the angle of C: "))
            if check_negative(angle_c):
                angle_c = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    side_c = side_a**2 + side_b**2 - 2*side_a*side_b*math.cos(math.radians(angle_c))
    side_c = round(math.sqrt(side_c),2)
    return side_c

def volume():
    """ Calculate the volume of a Right Circular Cylinder.
    Returns:
        Volume of Right Circular Cylinder.
    """
    print ("o----------------------------------------------------o")
    print ("| Calculate the volume of a Right Circular Cylinder. |")
    print ("o----------------------------------------------------o")
    radius = None
    while radius is None:
        try:
            radius = float(input("Enter the radius: "))
            if check_negative(radius):
                radius = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    height = None
    while height is None:
        try:
            height = float(input("Enter the height: "))
            if check_negative(height):
                height = None
                print('Please enter a positive number.')
        except ValueError:
            print('Please enter a number.')
    vol = round(math.pi*(radius**2)*height,3)
    return vol

menu = {
    1: 'Generate Secure Password.',
    2: 'Calculate and Format a Percentage.',
    3: 'How many days from today until July 4, 2025?',
    4: 'Use the Law of Cosines to calculate the leg of a triangle.',
    5: 'Calculate the volume of a Right Circular Cylinder.',
    6: 'Exit',
}

def print_menu():
    """ Print menu items. """
    print ("o-----------------o")
    print ("| Lab 2 Main Menu |")
    print ("o-----------------o")
    for key, value in menu.items():
        print (key, '--', value)

def main():
    """ Main """
    while True:
        print_menu() # Display Menu Options
        option = None
        while option is None:
            try:
                option = int(input('Enter a selection: '))
            except ValueError:
                print('*** Invalid selection ***')
        if option == 1:
            output = pw_gen()
            print(f"*** Generated Secure Password -> {output} ***")
        elif option == 2:
            output = percentage()
            print(f"*** Percentage = {output} ***")
        elif option == 3:
            output = today_until()
            print(f"*** Days = {output} ***")
        elif option == 4:
            output = law_of_cosines()
            print(f"*** C = {output} ***")
        elif option == 5:
            output = volume()
            print(f"*** Volume = {output} ***")
        elif option == 6:
            print('*** Exiting Program. Thanks! ***')
            sys.exit() # Exit Main Program
        else:
            print('*** Invalid selection ***')

if __name__=='__main__':
    main()
            