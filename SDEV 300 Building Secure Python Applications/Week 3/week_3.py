"""
Student: Allen Taylor
Program: Input Validation
Class/Assignment: SDEV 300 6382 / Week 3 Discussion
Date: 1/23/2022
"""
def your_info():
    """Prompt for and return Info.
    Returns:
    tuple:Returns first_name, last_name and age
    """
    first_name, last_name, age = None, None, None
    while first_name is None or first_name == "":
        first_name = input("Enter your first name: ")
    while last_name is None or last_name == "":
        last_name = input("Enter your last name: ")
    while age is None:
        try:
            age = int(input("Enter your age: "))
            if age < 1 or age > 120:
                age = None
                print("Invalid age. Try Again.")
        except ValueError:
            print("Please enter a valid age.")
    return (first_name, last_name, age)

if __name__ == "__main__":
    fn, ln, a = your_info()
    print("="*25)
    print(f"First Name: {fn} \nLast Name: {ln} \nAge: {a}")
    print("="*25)
        