"""
__filename__ = "lab_5.py"
__coursename__ = "SDEV 300 6380 - Building Secure Python Applications"
__author__ = "Allen Taylor"
__copyright__ = "None"
__credits__ = ["Allen Taylor"]
__license__ = "GPL"
__version__ = "1.0.0"
__maintainer__ = "Allen Taylor"
__email__ = "ataylor195@student.umgc.edu"
__status__ = "None"
"""
import pandas
import numpy as np   
from matplotlib import pyplot as plt


def read_data(filename):
    try:
        data = pandas.read_csv(filename)
    except FileNotFoundError:
        print("File Not Found!")
    else:
        return data

def statistics(data):
    array = np.array(data)
    print("The statistics for this column are:")
    print(f"Count = {len(array)} \nMean = {array.mean()} \nStandard Deviation = {array.std()} \nMin = {array.min()} \nMax = {array.max()}")
    histogram(array)

def histogram(array): 
    plt.hist(array, edgecolor="black", bins=10)
    plt.tight_layout()
    plt.show()

def banner():
    """ Print menu items. """
    print ("o-----------------o")
    print ("| Lab 5 Main Menu |")
    print ("o-----------------o")


menu_1 = {
    1: 'Population Data ',
    2: 'Housing Data',
    3: 'Exit the Program'
    }

menu_2 = {
    1: "Pop Apr 1", 
    2: "Pop Jul 1", 
    3: "Change Pop",
    4: "Exit to Main Menu"
}

menu_3 = {
    1: "AGE", 
    2: "BEDRMS", 
    3: "BUILT",
    4: "ROOMS",
    5: "UTILITY",
    6: "Exit to Main Menu"
}

def print_menu(menu):
    for key, value in menu.items():
        print (key, '--', value)

def main():


    '''
    age = housing_data["AGE"]
    bedrooms = housing_data["BEDRMS"]
    built = housing_data["BUILT"]
    rooms = housing_data["ROOMS"]
    utility = housing_data["UTILITY"] 
    pop_apr_1 = pop_data["Pop Apr 1"]
    pop_jul_1 = pop_data["Pop Jul 1"]
    change_pop = pop_data["Change Pop"]
    '''



if __name__ == "__main__":
    main()