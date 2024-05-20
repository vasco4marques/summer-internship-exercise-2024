package com.premiumminds.internship.teknonymy;

import java.time.LocalDateTime;

import com.premiumminds.internship.teknonymy.Person;

class TeknonymyService implements ITeknonymyService {

  // Nested class to store the result from a deep search within the family tree
  private static class TeknonumyDeepSearcher {
    Person person;
    int layer;

    public TeknonumyDeepSearcher(Person person, int layer) {
      this.person = person;
      this.layer = layer;
    }
  }

  /**
   * Method to get a Person Teknonymy Name
   * 
   * @param Person person
   * @return String which is the Teknonymy Name
   */

  public String getTeknonymy(Person person) {
    // If the person has no children returns ""
    // If the person has children. For each children that has children we need to go
    // deeper into each person's childern
    // unitil anyone has any children

    // Get the oldest descendant of the farthest generation of the family tree
    TeknonumyDeepSearcher oldestDescendant = findOldestDescendant(person, 0);

    String teknonymy = "";
    // Return "" if no one is found
    if (oldestDescendant.person == null) {
      return teknonymy;
    }

    int layer = oldestDescendant.layer;

    // If the oldest descendant is male
    if (oldestDescendant.person.sex() == 'M') {
      // If there is 1 layer between both people that means the is father
      if (layer == 1) {
        teknonymy = "father of " + oldestDescendant.person.name();
      } else {
        // If layer > 1 it means its a grandfather or great*-grandfather (as many greats
        // as levels above father and grandfather)
        teknonymy = "great-".repeat(layer - 2) + "grandfather of " + oldestDescendant.person.name();
      }

    } else {
      // If there is 1 layer between both people that means the is mother

      if (layer == 1) {
        teknonymy = "mother of " + oldestDescendant.person.name();
      } else {
        // If layer > 1 it means its a grandmother or great*-grandmother (as many greats
        // as levels above mother and grandmother)
        teknonymy = "great-".repeat(layer - 2) + "grandmother of " + oldestDescendant.person.name();
      }
    }

    return teknonymy;

  };

  public TeknonumyDeepSearcher findOldestDescendant(Person person, int layer) {
    Person[] children = person.children();
    String teknonymy = "";

    if (children.length == 0) {
      new TeknonumyDeepSearcher(null, layer);
    }

    TeknonumyDeepSearcher olderDescendant = new TeknonumyDeepSearcher(null, layer);
    for (Person child : children) {
      TeknonumyDeepSearcher descendant = findOldestDescendant(child, layer + 1);

      // If the person returned by the deepsearcher is not null and if the
      // olderDescendant is still nulll or our deepSearcher found a deeper descendant
      // then the actual one, we replace
      if (descendant.person != null) {
        if (olderDescendant.person == null || descendant.layer > olderDescendant.layer
            || (descendant.layer == olderDescendant.layer
                && descendant.person.dateOfBirth().isBefore(olderDescendant.person.dateOfBirth()))) {
          olderDescendant = descendant;
        }
      }

    }
    return olderDescendant;
  }
}
