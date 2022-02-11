"""
__filename__ = "lab_3.py"
__coursename__ = "SDEV 300 6380 - Building Secure Python Applications"
__lab__= "Python State Capital and Flower List Application Code"
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
from PIL import Image, UnidentifiedImageError
import requests
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
from state_data import state_data #import state_data dictionary

def sort_dict(s_d):
    """Sort State Data

    Parameters:
    s_d (dict): Unsorted State Data Dictionary

    Returns:
    dict: Sorted State Data Dictionary

   """
    s_d_sorted = {}
    for i in sorted(s_d):
        s_d_sorted[i]=s_d[i]
    return s_d_sorted

def normalize(state):
    """Normalize State Data

    Parameters:
    state (str): State string to normalize
    Returns:
    state_str (str): Normalized String

	"""
    state = state.lower()
    state_array = state.split(' ')
    state_str = ""
    for i, _ in enumerate(state_array):
        state_str = state_str + state_array[i].capitalize() + " "
    state_str = state_str.strip()
    return state_str

def display_state_data(s_d):
    """Displays State Data

    Parameters:
    s_d (dict): Sorted State Data Dictionary

	"""
    print("-"*20)
    sorted_data = sort_dict(s_d)
    for key, value in sorted_data.items():
        print("State: "+key)
        print("Capital: "+value["Capital"])
        print("Population: "+value["Population"])
        print("State Flower: "+value["State Flower"])
        print("-"*20)

def search_state_data(s_d, state):
    """Search State Data

    Parameters:
    s_d (dict): State Data Dictionary
    state (str): State to search

	"""
    if state in s_d.keys():
        print("-"*20)
        print("State: "+ state)
        print("Capital:", s_d[state]["Capital"])
        print("Population:", s_d[state]["Population"])
        print("State Flower:", s_d[state]["State Flower"])
        print("-"*20)
        try:
            display_image(s_d[state]["URL"])
        except ConnectionError:
            print("*** URL Unavailable. ***")
        except UnidentifiedImageError:
            print("*** Unable to load image. ***")
    else:
        print("*** Enter a valid state. ***")

def display_image(url):
    """Displays Image from URL derived from the State Data Dictionary

    Parameters:
    url (str): Image URL

	"""
    response = requests.get(url, stream=True)
    img = Image.open(response.raw)
    img.show()

def get_top_5(s_d):
    """Displays Barplot with Top 5 state populations

    Parameters:
    s_d (dict): State Data Dictionary

	"""
    pop_dict = {}
    data = []
    labels = []
    for key, value in s_d.items():
        pop_dict[key] = int(value["Population"])
    sort_pop_list = sorted(pop_dict, key=pop_dict.get, reverse=True)
    sort_pop_list = sort_pop_list[0:5]
    for state in sort_pop_list:
        data.append(pop_dict[state])
        labels.append(state)
    d_f = pd.DataFrame({"State":labels, "Population":data})
    plt.figure(figsize=(15, 8))
    plt.ticklabel_format(style='plain')
    splot=sns.barplot(x="State",y="Population",data=d_f)
    plt.xlabel("States\n", size=16)
    plt.ylabel("Population\n", size=16)
    plt.title("Top 5 Populated States")
    plt.bar_label(splot.containers[0],size=14, fmt = '%d')
    plt.show()

def update_pop(s_d, state, population):
    """Update population for a specific state

    Parameters:
    s_d (dict): State Data Dictionary
    state (str): State to search
    population (int): New population value

	"""
    if state in s_d.keys():
        s_d[state]["Population"] = population
        print(f"*** Population for {state} has been updated to {population}! ***")
    else:
        print("*** Enter a valid state. ***")

menu = {
    1: 'Display all U.S. States in Alphabetical order along with the Capital, State Population, and Flower.',
    2: 'Search for a specific state and display the appropriate Capital name, State Population, and an image of the associated State Flower',
    3: 'Provide a Bar graph of the top 5 populated States showing their overall population.',
    4: 'Update the overall state population for a specific state.',
    5: 'Exit the program.',
}

def print_menu():
    """ Print menu items. """
    print ("o-----------------o")
    print ("| Lab 3 Main Menu |")
    print ("o-----------------o")
    for key, value in menu.items():
        print (key, '--', value)

def main():
    """ Main """
    while True:
        print_menu()
        option = None
        while option is None:
            try:
                option = int(input('Enter a selection: '))
            except ValueError:
                print('*** Invalid selection ***')
        if option == 1:
            display_state_data(state_data)
        elif option == 2:
            state = None
            while state is None:
                state = input("Enter the State Name (Ex. New York, Texas, Florida): ")
                if state is None or state == '':
                    print("State cannot be blank!")
                    state = None
            state = normalize(state)
            search_state_data(state_data, state)
        elif option == 3:
            get_top_5(state_data)
        elif option == 4:
            state = None
            while state is None:
                state = input("Enter the State Name (Ex. New York, Texas, Florida): ")
                if state is None or state == '':
                    print("Invalid state name!")
                    state = None
            pop = None
            while pop is None:
                pop = int(input("Enter the new population number: "))
                if pop is None or pop == '' or pop < 100_000 or pop > 90_000_000:
                    print("Invalid population!")
                    pop = None
            state = normalize(state)
            update_pop(state_data, state, pop)
        elif option == 5:
            print('*** Exiting Program. Thanks! ***')
            sys.exit()
        else:
            print('*** Invalid selection ***')

if __name__ == "__main__":
    main()
    