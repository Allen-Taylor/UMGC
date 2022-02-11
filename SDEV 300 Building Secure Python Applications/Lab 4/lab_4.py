"""
__filename__ = "lab_4.py"
__coursename__ = "SDEV 300 6380 - Building Secure Python Applications"
__lab__= "Python Numpy and Pandas Application Code"
__author__ = "Allen Taylor"
__copyright__ = "None"
__credits__ = ["Allen Taylor"]
__license__ = "GPL"
__version__ = "1.0.0"
__maintainer__ = "Allen Taylor"
__email__ = "ataylor195@student.umgc.edu"
__status__ = "None"
"""

import sys
import re
import numpy as np

def validate_phone_number(phonenumber):
    """Validates Phone Number format

    Parameters:
    phonenumber (str): Phone Number string

    Returns:
    bool: Returns boolean
    """
    regex= r"^\d{3}-\d{3}-\d{4}"
    if re.search(regex, phonenumber):
        if len(phonenumber) == 12:
            return True
    return False

def validate_zip(zipcode):
    """Validates zipcode format

    Parameters:
    zipcode (str): zipcode string

    Returns:
    bool: Returns boolean
    """
    regex= r"^\d{5}(?:[-]\d{4})$"
    if re.search(regex, zipcode):
        if len(zipcode) == 10:
            return True
    return False

def check_row(row):
    """Checks formatting of Matrix row

    Parameters:
    row (str): Three numbers separated by two commas

    Returns:
    bool: Returns boolean
    """
    str_arr = row.split(",")
    try:
        float_arr = [float(i) for i in str_arr]
        for num in float_arr:
            if not isinstance(num, float):
                return False
        if len(float_arr) != 3:
            return False
        return True
    except ValueError:
        return False

def is_int(input_float):
    """Convert float to int type if possible to remove trailing zero

    Parameters:
    input_float (float): float

    Returns:
    int or float: Returns int or float
    """
    if input_float%1 == 0:
        return int(input_float)
    return input_float

def convert_ints(input_list):
    """Convert list of floats to ints if possible

    Parameters:
    input_list (list): list of floats

    Returns:
    list: Returns a list of floats, ints or mixed
    """
    for i, _ in enumerate(input_list):
        input_list[i] = is_int(input_list[i])
    return input_list

def convert_row(row):
    """Convert str into a row of floats, ints or mixed

    Parameters:
    row (str): row string

    Returns:
    list: Returns a list of floats, ints or mixed
    """
    row = row.split(",")
    row = [float(i) for i in row]
    row = convert_ints(row)
    return row

def gen_matrix():
    """Generate 3x3 Matrix

    Returns:
    list: Returns a list of three lists
    """
    while True:
        first_row = input("Enter first row (Example: #,#,#): ")
        if check_row(first_row):
            first_row = convert_row(first_row)
            break
        print("*** Please enter three integers separated by commas (Example: #,#,#). ***")
    while True:
        second_row = input("Enter second row (Example: #,#,#): ")
        if check_row(second_row):
            second_row = convert_row(second_row)
            break
        print("*** Please enter three integers separated by commas (Example: #,#,#). ***")
    while True:
        third_row = input("Enter third row (Example: #,#,#): ")
        if check_row(third_row):
            third_row = convert_row(third_row)
            break
        print("*** Please enter three integers separated by commas (Example: #,#,#). ***")
    matrix = [first_row, second_row, third_row]
    return matrix

def print_matrix(matrix):
    """ Print 3x3 Matrix """
    print("********************************************")
    print(f"{matrix[0][0]} {matrix[0][1]} {matrix[0][2]}")
    print(f"{matrix[1][0]} {matrix[1][1]} {matrix[1][2]}")
    print(f"{matrix[2][0]} {matrix[2][1]} {matrix[2][2]}")
    print("********************************************")

def banner():
    """ Print menu items. """
    print ("o-----------------o")
    print ("| Lab 4 Main Menu |")
    print ("o-----------------o")

menu = {
    1: 'Addition',
    2: 'Subtraction',
    3: 'Matrix Multiplication',
    4: 'Element by element multiplication',
    }

def print_matrix_operations():
    """ Print Matrix Operations. """
    for key, value in menu.items():
        print (key, '--', value)

def add(mat_1, mat_2):
    """Add two 3x3 matrices

    Parameters:
    mat_1 (list): list of lists
    mat_2 (list): list of lists
    """
    arr1 = np.array(mat_1)
    arr2 = np.array(mat_2)
    result = np.add(arr1,arr2)
    print("You selected Addition. The results are:")
    print_matrix(result)
    return result

def subtract(mat_1, mat_2):
    """Subract two 3x3 matrices

    Parameters:
    mat_1 (list): list of lists
    mat_2 (list): list of lists
    """
    arr1 = np.array(mat_1)
    arr2 = np.array(mat_2)
    result = np.subtract(arr1,arr2)
    print("You selected Subtraction. The results are:")
    print_matrix(result)
    return result

def mat_mult(mat_1, mat_2):
    """Matrix multiplication of two 3x3 matrices

    Parameters:
    mat_1 (list): list of lists
    mat_2 (list): list of lists
    """
    arr1 = np.array(mat_1)
    arr2 = np.array(mat_2)
    result = np.matmul(arr1,arr2)
    print("You selected Matrix multiplication. The results are:")
    print_matrix(result)
    return result

def mult(mat_1, mat_2):
    """Element by element multiplication of two 3x3 matrices

    Parameters:
    mat_1 (list): list of lists
    mat_2 (list): list of lists
    """
    arr1 = np.array(mat_1)
    arr2 = np.array(mat_2)
    result = np.multiply(arr1,arr2)
    print("You selected Element by element multiplication. The results are:")
    print_matrix(result)
    return result

def trans(results):
    """Transpose two 3x3 matrices

    Parameters:
    mat_1 (list): list of lists
    """
    arr1 = np.array(results)
    result = np.transpose(arr1)
    print("The Transpose is:")
    print_matrix(result)


def mean(results):
    """Row and column mean values for two 3x3 matrices

    Parameters:
    mat_1 (list): list of lists
    mat_2 (list): list of lists
    """
    arr1 = np.array(results)

    rows = arr1.mean(axis=1)
    columns = arr1.mean(axis=0)
    print("The row and column mean values of the results are:")
    print("********************************************")
    print(f"Rows: {rows[0]} {rows[1]} {rows[2]}")
    print(f"Columns: {columns[0]} {columns[1]} {columns[2]}")
    print("********************************************")

def main():
    """ Main """

    #Valid Responses
    valid_res = ("YES","Y","NO","N")
    no_res = ("NO","N")
    while True: # Main Loop
        banner()
        #Matrix Game Prompt
        while True:
            matrix_game = str(input("Do you want to play the Matrix Game?\nEnter Y for Yes or N for No: ")).upper()
            if matrix_game in valid_res:
                if matrix_game in no_res:
                    print("*** Exiting Matrix Game! Goodbye! ***!")
                    sys.exit()
                break
            print("*** Enter a valid response. ***")

        #Phone Number Prompt
        while True:
            phone_number = input("Enter your phone number (XXX-XXX-XXXX): ")
            if validate_phone_number(phone_number):
                break
            print("*** Enter a valid phone number with the (XXX-XXX-XXXX) format. ***")

        #Zip Code Prompt
        while True:
            zip_code = input("Enter your zip code +4 (XXXXX-XXXX): ")
            if validate_zip(zip_code):
                break
            print("*** Enter a zip code +4 with the (XXXXX-XXXX) format. ***")

        #1st 3x3
        print("Enter your first 3x3 matrix:")
        matrix1 = gen_matrix()
        print("Your first 3x3 matrix is:")
        print_matrix(matrix1)

        #2st 3x3
        print("Enter your second 3x3 matrix:")
        matrix2 = gen_matrix()
        print("Your second 3x3 matrix is:")
        print_matrix(matrix2)

        #Matrix Operations
        while True:
            print("Select a Matrix Operation from the list below:")
            print_matrix_operations()
            option = None
            while option is None:
                try:
                    option = int(input('Enter a selection: '))
                except ValueError:
                    print('*** Invalid selection ***')
            if option == 1:
                res = add(matrix1, matrix2)
                trans(res)
                mean(res)
                break
            elif option == 2:
                res = subtract(matrix1, matrix2)
                trans(res)
                mean(res)
                break
            elif option == 3:
                res = mat_mult(matrix1, matrix2)
                trans(res)
                mean(res)
                break
            elif option == 4:
                res = mult(matrix1, matrix2)
                trans(res)
                mean(res)
                break
            else:
                print('*** Invalid selection ***')

if __name__ == "__main__":
    main()
    