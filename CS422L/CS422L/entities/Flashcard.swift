//
//  Flashcard.swift
//  CS422L
//
//  Created by Jonathan Sligh on 2/3/21.
//

import Foundation

class Flashcard {
    var term: String = ""
    var definition: String = ""
    
    static func getHardCodedCollection() -> [Flashcard]
    {
        var flashcards = [Flashcard]()
        for i in 1...10
        {
            let flashcard = Flashcard()
            flashcard.term = "Term \(i)"
            flashcard.definition = "Definition \(i)"
            flashcards.append(flashcard)
        }
        return flashcards
    }
}
