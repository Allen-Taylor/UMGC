"""
Student: Allen Taylor
Program: Voter Registration Application
Class/Homework: SDEV 300 6382 - Lab 1
Date: 1/9/2022
"""
import re
STATES = ('AL', 'AK', 'AZ' ,'AR', 'CA', 'CO', 'CT', 'DE', 'FL', 'GA', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MD', 'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND', 'OH', 'OK', 'OR', 'PA', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VA', 'WA', 'WV', 'WI', 'WY')

def question_str(question):
    """Input validation, string only."""
    while True:
        res = input(f"{question}:\n")
        if res:
            res_bool = bool(re.search("^[a-zA-Z]+$",res))
            if res_bool:
                break
            print("Invalid Entry.")
        else:
            print("Invalid Entry.")
    return res

def question_age():
    """Input validation for age."""
    age_check = True
    while age_check:
        try:
            age = int(input("Age:\n"))
        except ValueError:
            print("Invalid Entry.")
        else:
            if age < 1 or age > 120:
                print("Invalid Entry.")
            else:
                age_check = False
    return age

def question_state():
    """Input validation for State."""
    while True:
        res = input("State of Residence [Example: NY, FL, TX]:\n")
        if res:
            try:
                STATES.index(res)
                break
            except ValueError:
                print("Invalid Entry.")
        else:
            print("Invalid Entry.")
    return res

def question_zip():
    """Input validation for Zip Code"""
    while True:
        zipcode = input("ZIP Code [Format: xxxxx or xxxxx-xxxx]:\n")
        match= bool(re.search(r'^\d{5}(?:[-\s]\d{4})?$', zipcode))
        if match:
            break
        print("Invalid Entry.")
    return zipcode

def confirm(app):
    """Vote information confirmation"""
    print("=========================================")
    print("Please confirm the following information:")
    print("=========================================")
    for key, value in app.items():
        print(f"{key}: {value}")
    print("=========================================")
    while True:
        res = input("Information is correct? [Yes/No]:\n")
        if res in ("Yes","No","Y","N","yes","no","y","n"):
            break
        print("Invalid input. Please type 'Yes' or 'No'.")
    return res

def cont():
    """Allows the user to exit the program."""
    while True:
        res = input("Continue? [Yes/No]:\n")
        if res in ("Yes","No","Y","N","yes","no","y","n"):
            break
        print("Invalid input. Please type 'Yes' or 'No'.")
    return res

def banner():
    """Program banner"""
    print("=============================================")
    print("Welcome to the Python Voter Registration App!")
    print("=============================================")

def main():
    """Main Program"""
    application = {}
    banner()
    while True:
        res = input("Proceed with Voter Registration? [Yes or No]:\n")
        if res in ("Yes","Y","yes","y"):
            coc = question_str("Country of Citizenship [Example: USA]")
            if coc != "USA":
                print("Unfortunately, you are ineligible to vote. Exiting program.")
                break
            application["Country of Citizenship"] = coc
            res = cont()
            if res in ("No","N","no","n"):
                print("Now exiting the Python Voter Registration App! Goodbye!")
                break
            age = question_age()
            if age < 18:
                print("Unfortunately, you are ineligible to vote. Exiting program.")
                break
            application["Age"] = age
            res = cont()
            if res in ("No","N","no","n"):
                print("Now exiting the Python Voter Registration App! Goodbye!")
                break
            application["First Name"] = question_str("First Name")
            res = cont()
            if res in ("No","N","no","n"):
                print("Now exiting the Python Voter Registration App! Goodbye!")
                break
            application["Last Name"] = question_str("Last Name")
            res = cont()
            if res in ("No","N","no","n"):
                print("Now exiting the Python Voter Registration App! Goodbye!")
                break
            application["State of Residence"] = question_state()
            res = cont()
            if res in ("No","N","no","n"):
                print("Now exiting the Python Voter Registration App! Goodbye!")
                break
            application["Zipcode"] = question_zip()
            confirm_res = confirm(application)
            if confirm_res in ("Yes","Y","yes","y"):
                print("Thank you for submitting your voter registration. Have a wonderful day.")
                break
            print("Please re-enter your voter information.")
        elif res in ("No","N","no","n"):
            print("Now exiting the Python Voter Registration App! Goodbye!")
            break
        else:
            print("Please enter a valid response.")
if __name__ == "__main__":
    main()
