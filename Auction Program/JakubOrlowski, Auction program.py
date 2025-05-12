# Author: Jakub Orlowski
# Date started: 16/04/2025
# Project for Modular programming year 1 semester 2

from datetime import date


# load data function
def load_data() -> tuple[list[str], list[float], list[float], str]:
    """
    This function takes information from auction.txt and puts it into 3 lists
    :param: none
    :return: painting_names, starting_bid, current_bid, auction file
    """
    auction = "auction.txt"
    while True:
        try:
            painting_names = []
            starting_bid = []
            current_bid = []
            with open(auction, 'r') as auction_data:
                for line in auction_data:
                    line = line.strip()
                    line_info = line.split(',')
                    painting_names.append(line_info[0])
                    starting_bid.append(int(line_info[1]))
                    current_bid.append(int(line_info[2]))
            return painting_names, starting_bid, current_bid, auction
        except FileNotFoundError:
            print(f'Error occurred, {auction} not found.')
            exit()


# menu function

def menu(painting_names, starting_bid, current_bid, auction):
    """
    This is the menu function which is perma on loop.
    It only exits the loop if the option 7 is used.

    :param painting_names:
    :param starting_bid:
    :param current_bid:
    :param auction:
    :return:
    """
    while True:
        print("1. Record a new bid")
        print("2. Log current auction information")
        print("3. Filter within desired budget")
        print("4. Commission prices and Status")
        print("5. Highest bid for painting")
        print("6. Average price of painting, and average starting bid")
        print("7. Exit ")
        try:
            selection = int(input("Which option would you like to choose?:"))
            if selection >= 8:
                print("Error, Please select a provided option")
                continue
            elif selection == 0:
                print("Error, Please select a provided option")
                continue
            elif selection == 1:
                new_bid(painting_names, starting_bid, current_bid, auction)
            elif selection == 2:
                auction_log(painting_names, starting_bid, current_bid)
            elif selection == 3:
                desired_choices(painting_names, starting_bid, current_bid)
            elif selection == 4:
                commission_rating("Commission Price", current_bid)
            elif selection == 5:
                high_bid, high_name = highest_bid(painting_names, current_bid)
                print(f'The highest bid is {high_bid}\n'
                      f'For the painting: {high_name}')
            elif selection == 6:
                average_starting_bid, average_price = average_values(starting_bid, current_bid)
                print(f'The average starting bid in this auction is: {average_starting_bid}\n'
                      f'The average price of paintings current is: {average_price}')
            elif selection == 7:
                print("Are you sure you want to exit?")
                confirm = str(input("Yes/No:"))
                if confirm.lower() == "yes":
                    exit()
                else:
                    continue
        except ValueError:
            print(f'Error, Please select a provided option:')
            continue


# Rewrite and save everything function

def rewrite_file(painting_names, starting_bid, current_bid, auction):
    """
    This function overwrites the auction file with the updated lists.
    Each line is in the format: painting_name, starting_bid, current_bid

    :param painting_names:
    :param starting_bid:
    :param current_bid:
    :param auction:
    :return: none
    """
    with open(auction, 'w') as file:
        for painting, start, current in zip(painting_names, starting_bid, current_bid):
            file.write(f'{painting}, {start}, {current}\n')


# New bid function

def new_bid(painting_names, starting_bid, current_bid, auction):
    """
    records a new bid, it lists all the available paintings for auction then asks the user to choose a painting
    determines minimum bid permitted and forces user to bid higher than the bid placed or minimum bid

    :param painting_names:
    :param starting_bid:
    :param current_bid:
    :param auction: the auction information file
    :return:
    """
    print("Recording new bid")
    print("Which painting would you like to bid for?")
    for i, name in enumerate(painting_names):
        if current_bid[i] == 0:
            known_bid = "No bid placed"
        else:
            known_bid = current_bid[i]
        print(f'{i}, "{name}"\n The starting bid is: {starting_bid[i]} \n Current bid status: {known_bid}')

    try:
        painting_choice = int(input("Which painting would you like to bid on?:"))
        if painting_choice < 0 or painting_choice >= len(painting_names):
            print("Invalid choice.")
            return
    except ValueError:
        print("Please enter a valid number.")
        return

    print(f"Bidding for painting {painting_names[painting_choice]}")
    print(f"Current bid: {current_bid[painting_choice]}")
    print(f"Starting bid: {starting_bid[painting_choice]}")

    try:
        user_bid = round(int(input("Please input your bid: ")))
    except ValueError:
        print("Invalid bid. Please enter a numeric value.")
        return

    if current_bid[painting_choice] == 0:
        min_bid = starting_bid[painting_choice]
    else:
        min_bid = current_bid[painting_choice]

    if user_bid < min_bid:
        print(f"Your bid must be at least {min_bid}")
        return

    print(f"Your bid for this painting is now {user_bid}")
    confirm = input("Would you like to confirm the bid? Yes/No:")
    if confirm.lower() == "no":
        return
    elif confirm.lower() == "yes":
        print("Your bid has been placed")
        current_bid[painting_choice] = user_bid
        rewrite_file(painting_names, starting_bid, current_bid, auction)
    else:
        print("Please enter correct option, Bid cancelled")
        return


def auction_log(painting_names, starting_bid, current_bid):
    """
    This function creates a file with today's date using the date. today function from the date time module
    It puts the current auction details as a sort of log
    :param painting_names:
    :param starting_bid:
    :param current_bid:
    :return:
    """
    today = date.today()
    log = f"Bid_Log{today}"
    with open(log, 'w') as file:
        print("Logging data")
        print("Logged data:")
        for names, start, current in zip(painting_names, starting_bid, current_bid):
            file.write(f"{names}, {start}, {current}\n")
            print(f"{names}, {start}, {current}\n")
        print("------------------------------------")


def desired_choices(painting_names, starting_bid, current_bid):
    """
    This function takes an input from the user and calculated the upper and lower "budget" which is done via adding 20%
    and subtracting 20%

    --Note--
    In my opinion it should only the upper limit calculated and the lower limit should be 0. However, the project spec
    says otherwise.
    --   --
    :param painting_names:
    :param starting_bid:
    :param current_bid:
    :return:
    """
    lower_limit = 0
    upper_limit = 0
    found_match = False
    try:
        desired_price = int(input("Enter a desired budget:"))
        lower_limit = desired_price - (desired_price * .2)
        upper_limit = desired_price + (desired_price * .2)
    except ValueError:
        print("Please enter a valid amount")
    print("Available bidding options within your desired budget")
    for name, start, current in zip(painting_names, starting_bid, current_bid):
        if current == 0:
            if lower_limit < start < upper_limit:
                print(f"{name} {start} {current}")
                found_match = True

        elif lower_limit < current < upper_limit:
            print(f"{name} {start} {current}")
            found_match = True
    if not found_match:
        print("Nothing fits within your desired budget")


def commission_rating(auction_name, current_bid):
    """
    This function creates a file that lists the commission prices for each current bid placed.
    The name of the file is called into the function as a parameter, however. It stays stacking, therefore
    no extra files will be created. They will always be overwritten and only 1 will exist
    :param auction_name:
    :param current_bid:
    :return:
    """
    file_name = f"{auction_name}"
    with open(file_name, 'w') as commission:
        commission.write("Current_Bid, - ,Commission\n")
        for i in current_bid:
            if i < 2000:
                commission_fee = i * .1
            elif i > 2000:
                commission_fee = i * .2
            commission.write(f'{i}  - - - - - {commission_fee}\n')
    print(f"Snapshot of commission status has been taken, see {auction_name}")
    return


def highest_bid(painting_names, current_bid) -> tuple[int, str]:
    """
    This function takes painting names and current bid to find the painting with the highest bid
    The specification only asked for the name of the painting however I feel including the bid amount would also be
    very user-friendly if this code was to be used in a real world scenario. Hence, 2 return values instead of 1.
    :param painting_names:
    :param current_bid:
    :return: High_bid
    :return: High_name
    """
    high_bid = 0
    high_name = ""
    for i, bid in enumerate(current_bid):
        if bid > high_bid:
            high_bid = bid
            high_name = painting_names[i]
        else:
            continue
    return high_bid, high_name


def average_values(starting_bid, current_bid) -> tuple[int, int]:
    """
    The average values function calculated the average values for the starting bid of the entire auction
    and the average price of paintings in the auction. This however is subject to change with each bid.
    The file does not update on bid, but only when this function is called.
    Calling this function automatically from within the new_bid function would be more useful. !
    :param starting_bid:
    :param current_bid:
    :return:
    """
    # Average starting bid
    all_starting_bids = 0
    count = 0
    for i in starting_bid:
        all_starting_bids = all_starting_bids + i
        count = count + 1
    average_starting_bid = all_starting_bids / count
    # Average price of paintings
    all_current_bids = 0
    cur_count = 0
    for i in current_bid:
        all_current_bids = all_current_bids + i
        cur_count = cur_count + 1
    average_price = all_current_bids / cur_count
    return average_starting_bid, average_price


def main():
    painting_names, starting_bid, current_bid, auction = load_data()
    menu(painting_names, starting_bid, current_bid, auction)


main()
