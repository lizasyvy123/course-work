package com.nulp.course_work.sortfilter;


import com.nulp.course_work.constants.flowertype;
import com.nulp.course_work.constants.ordersort;
import com.nulp.course_work.items.flower;

import java.util.ArrayList;

import static com.nulp.course_work.Bouquet.bouquetflowers;
import static com.nulp.course_work.constants.flowertype.getflowertype;

public class FilterflowersWithEntering {

    // Static arraylist of current filtering/sorting of flowers
    public static ArrayList<flower> filteredflowers = new ArrayList<>();




    /**
     * The method that sorts all flowers in ascending or descending order of freshness that not in use FX
     * This method calls for the user to enter the sorting parameters
     * @return The list of all flowers in ascending or descending order of freshness
     */
    public static ArrayList<flower> sortflowersByFreshFX(ordersort sort){
        SortFilterflowersDB filtrationflower = new SortFilterflowersDB();
        filteredflowers = filtrationflower.sortByFresh(sort);
        filteredflowers.removeAll(bouquetflowers);
        return filteredflowers;
    }
    /**
     * The method that returns all flowers that not in use
     * This method calls for the user to enter the filter parameters
     * @return The list of all flowers that not used
     */
    public static ArrayList<flower> filterAllflowers(){
        SortFilterflowersDB filtrationflower = new SortFilterflowersDB();
        filteredflowers = filtrationflower.getAll();
        filteredflowers.removeAll(bouquetflowers);
        return filteredflowers;
    }




    /**
     * The method that searches for flowers of a given type that not in use
     * This method calls for the user to enter the filter parameters
     * @return The list of flowers of a given type that not in use
     */
    public static ArrayList<flower> filterflowersByTypeFX(flowertype type){
        SortFilterflowersDB filtrationflower = new SortFilterflowersDB();
        filteredflowers = filtrationflower.sortByType(type);
        filteredflowers.removeAll(bouquetflowers);
        return filteredflowers;
    }


    /**
     * The method that searches for flowers in a price range that on in use
     * The method asks the user to enter the search parameters (minimum and maximum limit) FX
     * @return The list of flowers in the price range that not in use
     */
    public static ArrayList<flower> filterflowersInPriceRangeFX(Double enteredMin, Double enteredMax){
        SortFilterflowersDB filtrationflower = new SortFilterflowersDB();
        double min = filtrationflower.minPrice();
        double max = filtrationflower.maxPrice();


        filteredflowers = filtrationflower.filterInPriceRange(enteredMin, enteredMax);
        filteredflowers.removeAll(bouquetflowers);
        return filteredflowers;
    }



    /**
     * The method that searches for flowers in a length range that not in use
     * The method asks the user to enter the search parameters (minimum and maximum limit)
     * @return The list of flowers in the length range
     */
    public static ArrayList<flower> filterflowersInLengthRangeFX(Double enteredMin, Double enteredMax){
        SortFilterflowersDB filtrationflower = new SortFilterflowersDB();
        double min = filtrationflower.minLength();
        double max = filtrationflower.maxLength();

        filteredflowers = filtrationflower.filterInLengthRange(enteredMin, enteredMax);
        filteredflowers.removeAll(bouquetflowers);
        return filteredflowers;
    }

}
