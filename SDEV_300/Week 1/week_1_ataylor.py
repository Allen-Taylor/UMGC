"""
Creates and prints a Python dictionary with the user's information.
"""
def your_info():
    """Creates and returns a dictionary"""
    bio = {}
    bio["First Name"] = input("Enter your first name: ")
    bio["Last Name"] = input("Enter your last name: ")
    while True:
        try:
            age = int(input("Enter your age: "))
        except ValueError:
            print("Please enter a valid age.")
        else:
            bio["Age"] = age
            break
    return bio

if __name__ == "__main__":
    print("Let's create a Python dictonary with your information!")
    your_info_dict = your_info()
    print("Your information:")
    for key, value in your_info_dict.items():
        print(key, ':', value)
        