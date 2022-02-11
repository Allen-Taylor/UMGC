"""
| __filename__ = "voter_registration_lab1.py"
| __coursename__ = "SDEV 300 - Building Secure Web Applications"
| __author__ = "John Doe Student"
| __copyright__ = "None"
| __credits__ = ["John Doe"]
| __license__ = "GPL"
| __version__ = "SPRING_2022_6382"
| __maintainer__ = "John Doe"
| __email__ = "john.doe@student.umuc.edu"
| __status__ = "Baseline"
| __docformat__ = 'reStructuredText'
"""

import sys
import re
import logging
####################################################################
# Catch Exceptions and write them to log file in current folder.
####################################################################
logging.basicConfig(filename='./error.log',
                    format='%(asctime)s %(levelname)s:%(message)s',
                    datefmt='%Y-%m-%d %H:%M:%S',
                    level=logging.DEBUG)
####################################################################

#############
# CONSTANTS
#############
US_STATE_ABBREV = {
    'AL': 'Alabama',
    'AK': 'Alaska',
    'AZ': 'Arizona',
    'AR': 'Arkansas',
    'CA': 'California',
    'CO': 'Colorado',
    'CT': 'Connecticut',
    'DC': 'District of Columbia',
    'DE': 'Delaware',
    'FL': 'Florida',
    'GA': 'Georgia',
    'HI': 'Hawaii',
    'ID': 'Idaho',
    'IL': 'Illinois',
    'IN': 'Indiana',
    'IA': 'Iowa',
    'KS': 'Kansas',
    'KY': 'Kentucky',
    'LA': 'Louisiana',
    'ME': 'Maine',
    'MD': 'Maryland',
    'MA': 'Massachusetts',
    'MI': 'Michigan',
    'MN': 'Minnesota',
    'MS': 'Mississippi',
    'MO': 'Missouri',
    'MT': 'Montana',
    'NE': 'Nebraska',
    'NV': 'Nevada',
    'NH': 'New Hampshire',
    'NJ': 'New Jersey',
    'NM': 'New Mexico',
    'NY': 'New York',
    'NC': 'North Carolina',
    'ND': 'North Dakota',
    'OH': 'Ohio',
    'OK': 'Oklahoma',
    'OR': 'Oregon',
    'PA': 'Pennsylvania',
    'PR': 'Puerto Rico',
    'RI': 'Rhode Island',
    'SC': 'South Carolina',
    'SD': 'South Dakota',
    'TN': 'Tennessee',
    'TX': 'Texas',
    'UT': 'Utah',
    'VT': 'Vermont',
    'VA': 'Virginia',
    'WA': 'Washington',
    'WV': 'West Virginia',
    'WI': 'Wisconsin',
    'WY': 'Wyoming',
}


def main():
    """
    Voter Registration main()
    """
    # Menu
    print('*' * 88)
    print(' Welcome to the Python Voter Registration Application.')
    confirm_continue()
    age = validate_age()
    confirm_continue()
    us_citizen = confirm_continue(' Are you a U.S. Citizen? ',
                                  'Thanks for trying the Voter Registration'
                                  + ' Application. You need to be a U.S.'
                                  + ' Citizen to vote.',
                                  94)
    confirm_continue()
    first_name = validate_name(' What is your first name? ')
    confirm_continue()
    last_name = validate_name(' What is your last name? ')
    confirm_continue()
    state = validate_state(' What state do you live? ')
    confirm_continue()
    zip_code = validate_zipcode(' What is your zipcode? ')
    confirm_continue()
    print(' Thanks for registering to vote.',
          'Here is the information we received:')
    print("",
          "\t #######################################",
          f"\t #  Name (first last): {first_name} {last_name}",
          f"\t #  Age: {age}",
          f"\t #  U.S. Citizen: {us_citizen}",
          f"\t #  State: {state}",
          f"\t #  Zipcode: {zip_code}",
          "\t #######################################", "", sep='\r\n')

    print("\r\n ")
    print('*' * 88, "\r\n",
          'Thanks for trying the Voter Registration Application.', "\r\n",
          'Your voter registration card should be shipped within 3 weeks.')
    print('*' * 88)

    sys.exit(0)


def validate_name(default_prompt=None):
    """
    Capture and Validate Name meets naming requirement

    :param default_prompt(str): Value string to place in the input prompt

    :rtype: str
    :return: **selection(str)**: Name (First or Last) validated to match \
    A-Z, ' or, -
    """
    selection = str(input(default_prompt))
    # Handle A-Z a-z ' and - in name
    if not re.fullmatch('^[a-z \'A-Z]+(-[a-z A-Z]+)?$', selection):
        print("\r\n")
        print('*' * 93, "\r\n",
              'Thanks for trying the Voter Registration Application. Your',
              'name contains invalid characters')
        print('*' * 93)
        sys.exit(0)

    return selection


def validate_state(default_prompt=None):
    """
    Capture and Validate State meets naming requirement

    :param default_prompt(str): Value string to place in the input prompt

    :rtype: str
    :return: **selection(str)**: Valid (2) Character State
    """
    selection = str(input(default_prompt)).upper()
    # Handle A-Z a-z in State Name and must have (2) Letters
    while not re.fullmatch('^[A-Z][A-Z]$', selection):
        print(' Your state name contains invalid characters. Please enter',
              'a 2 Letter State Code.')
        selection = str(input(default_prompt))

    if selection not in US_STATE_ABBREV:
        print("\r\n ")
        print('*' * 110, "\r\n",
              'Thanks for trying the Voter Registration Application.',
              'You have entered an unknown state abbreviation of',
              f'"{selection}"')
        print('*' * 110)
        sys.exit(0)

    return selection


def validate_age():
    """
    Capture and Validate Age

    :rtype: str
    :return: **selection(int)**: Age >17 and less than 120. Age can be \
    returned be greater than 120 if confirmed by user.

    :raises:
        :ValueError: User input a non-int age

    """
    selection = 0
    while (selection <= 17) or (selection > 120):
        try:
            selection = int(input(' What is your age? '))
            if selection < 18:
                print("\r\n")
                print('*' * 88, "\r\n",
                      'Thanks for trying the Voter Registration Application.'
                      ' You are not old enough to vote.')
                print('*' * 88)
                sys.exit(0)

            if selection > 120:
                confirm_age = confirm_continue(' Are you really '
                                               + str(selection) + ' yrs old? ',
                                               'Please corrected your age.',
                                               exit_app=False)
                if confirm_age == 'YES':
                    # Exit while loop with age greater than 120 User Confirmed
                    break
                # else: # No else required due to break/if condition
                selection = 0

        except ValueError as not_an_int_error:
            selection = 0
            print(' Your age contains invalid characters. Please enter',
                  'only digits for your age.')
            logging.debug(" Exception Caught: %s: %s",
                          type(not_an_int_error).__name__,
                          not_an_int_error)

    return selection


def validate_zipcode(default_prompt=None):
    """
    Capture and Validate zip code meets mailing requirement. validate_zipcode
    function will capture zip code. If the format of the zip code is #####-####
    or ##### format, it returns zip code without any change. If the zip code is
    a 9 digit number, it will change the format from ######### to
    #####-####. Otherwise, keep prompting to get a valid zip code.

    :param default_prompt(str): Value string to place in the input prompt

    :rtype: str
    :return: **reformated_value(str)**: Valid 5 or 9 character zipcode
    """

    selection = str(input(default_prompt))
    five_digit_regex = r'(\d{5})'
    nine_digit_regex = r'(\d{5})-?(\d{4})'
    five_digit_zip_format = re.fullmatch(five_digit_regex, selection)
    nine_digit_zip_format = re.fullmatch(nine_digit_regex, selection)

    while five_digit_zip_format is None and nine_digit_zip_format is None:
        print(' Your zipcode is invalid. Please enter a valid zipcode in the',
              'format of #####-#### or #####.')
        selection = str(input(default_prompt))
        five_digit_zip_format = re.fullmatch(five_digit_regex, selection)
        nine_digit_zip_format = re.fullmatch(nine_digit_regex, selection)

    reformated_value = ""
    if five_digit_zip_format is not None:
        reformated_value = selection
    elif nine_digit_zip_format is not None:
        result = re.fullmatch(nine_digit_regex, selection)
        reformated_value = '-'.join(result.groups())

    return reformated_value


def confirm_continue(default_prompt=' Do you want to continue with Voter '
                     + 'Registration? (Y or N):',
                     exit_message='Thanks for trying the Voter Registration'
                     + ' Application. You have selected to exit.',
                     seperator_count=88, exit_app=True):
    """
    Confirm user wishes to proceed

    :param default_prompt(str): Value string to place in the input prompt. \
    Default: Do you want to continue with VoterRegistration? (Y or N):

    :param exit_message(str): Value string to place if application exit occurs.

    :param seperator_count(str): Border of * chars count surrounding \
    messages. Default: 88

    :param exit_app(bool): Should application exit on 'No'. Default: True

    :rtype: str
    :return:  **selection(str)**: Returns YES if successful.
    """
    valid_answer_set = ['Y', 'YES', 'N', 'NO']
    # User Input
    selection = None
    while selection not in valid_answer_set:
        selection = str(input(default_prompt)).upper()
        if selection not in valid_answer_set:
            print("",
                  " #######################################",
                  ' Please make a valid selection Y or N',
                  " #######################################", "", sep='\r\n')
    if selection in ['N', 'NO']:
        print("\r\n")
        print('*' * seperator_count, "\r\n",
              exit_message)
        print('*' * seperator_count)
        if exit_app:
            sys.exit(0)

    if selection == 'Y':
        selection = 'YES'

    return selection


if __name__ == "__main__":
    main()
