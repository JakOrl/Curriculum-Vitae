# Author: Jakub Orlowski
# date: 20/03/2025

# question 2
def get_location_year() -> tuple[str, int]:
    """
    asks for the location and the year when the cat tournament was held and stores it in
    location, year, variables
    the function then returns both of those variables one being a str and one being an int as shown in type hint
    :return: location, year
    """
    print("Please enter the information about the cat competition")
    location = input("Enter where the cat competition held place:")
    year = int(input("Enter the year the competition took place:"))
    return location, year


# Question 3
def get_cat_scores(list_cats) -> list[int]:
    """
     Making an empty list for the scores, the for each cat in the list of cats
    The user is asked to enter a score for a cat, the score is then saved as a variable and
    appended onto the list.
    the list of cats is returned in the type int as indicated in the type hint

    the list of cats is a parameter required by the function
    :param list_cats:
    :return: list of cats
    """
    score_list = []
    for cat in list_cats:
        score = int(input(f'Please enter grooming score for {cat}:'))
        score_list.append(score)
    return score_list


# question 4
def show_scores(list_cats, score_list):
    """
    this variable prints a table with each cats name and the corresponding score given to them
    there is no return from this function however the function takes 2 parameters, that being a list of scores
    and a list of cats
    :param list_cats:
    :param score_list:
    :return: none.
    """
    print("Cat Name      Score in Grooming")
    print("===============================")
    for i, cat in enumerate(list_cats):
        print(f'{cat}   ----- {score_list[i]} points')
    print("===============================")
    print(f'A total of {len(list_cats)} has participated in the competition')


# question 5
def winning_cat(list_cats, score_list) -> str:
    """
    this uses list of cats and scores and puts it in an enumerated for loop, storing the best score and cat in 2
    variables one of the variables is returned as the name of the best cat :param list_cats: :param score_list:
    :return: best cat
    """
    best_score = 0
    best_cat = ""
    for i, cat in enumerate(list_cats):
        if score_list[i] > best_score:
            best_score = score_list[i]
            best_cat = cat
    return best_cat


# main function all together including question 1
def main():
    cat_list = ["Whiskers", "Mittens", "Shadow", "Oreo"]
    location, year = get_location_year()
    score_list = get_cat_scores(cat_list)
    print(f'{location} {year} Cat Competition')
    show_scores(cat_list, score_list)
    best_cat = winning_cat(cat_list, score_list)
    print(f'The winning cat is {best_cat}')


main()
