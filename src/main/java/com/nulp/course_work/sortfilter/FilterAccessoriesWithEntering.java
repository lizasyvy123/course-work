package com.nulp.course_work.sortfilter;


import com.nulp.course_work.constants.accessorytype;
import com.nulp.course_work.items.accessory;

import java.util.ArrayList;

import static com.nulp.course_work.Bouquet.bouquetAccessories;
import static com.nulp.course_work.constants.accessorytype.getaccessorytype;

public class FilterAccessoriesWithEntering {

    // Static arraylist of current filtering/sorting of accessories
    public static ArrayList<accessory> filteredAccessories = new ArrayList<>();



    /**
     * The method that searches for accessories of a given type that not in use FX
     * This method calls for the user to enter the filter parameters
     * @return The list of accessories of a given type
     */
    public static ArrayList<accessory> filterAccessoriesByTypeFX(accessorytype type) {
        SortFilterAccessoriesDB sortFilterAccessoriesDB = new SortFilterAccessoriesDB();
        filteredAccessories = sortFilterAccessoriesDB.sortByType(type);
        filteredAccessories.removeAll(bouquetAccessories);
        return filteredAccessories;
    }

    /**
     * The method that returns all accessories that not in use
     * This method calls for the user to enter the filter parameters
     * @return The list of all accessories that not used
     */
    public static ArrayList<accessory> filterAllAccessories() {
        SortFilterAccessoriesDB sortFilterAccessoriesDB = new SortFilterAccessoriesDB();
        filteredAccessories = sortFilterAccessoriesDB.getAll();
        filteredAccessories.removeAll(bouquetAccessories);
        return filteredAccessories;
    }



    /**
     * The method that searches for accessories in a price range
     * The method asks the user to enter the search parameters (minimum and maximum limit)
     * @return The list of accessories in the price range
     */
    public static ArrayList<accessory> filterAccessInPriceRangeFX(double enteredMin, double enteredMax) {

        SortFilterAccessoriesDB filtrationaccessory = new SortFilterAccessoriesDB();

        double min = filtrationaccessory.minPrice();
        double max = filtrationaccessory.maxPrice();
        filteredAccessories = filtrationaccessory.filterInPriceRange(enteredMin, enteredMax);
        filteredAccessories.removeAll(bouquetAccessories);
        return filteredAccessories;
    }
}
