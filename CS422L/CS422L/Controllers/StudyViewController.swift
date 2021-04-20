//
//  StudyViewController.swift
//  CS422L
//
//  Created by Ryan Jones on 2/17/21.
//

import Foundation
import UIKit

class StudyViewController: UIViewController{
    
    @IBOutlet var cardView: UIView!
    @IBOutlet var cardTermLabel: UILabel!
    @IBOutlet var cardDefLabel: UILabel!
    var numberOfMissed: Int = 0
    var numberOfCorrect: Int = 0
    var numberOfCompleted: Int = 0
    var currentIndex: Int = 0
    
    @IBOutlet var skipButton: UIView!
    @IBOutlet var missedButton: UIView!
    @IBOutlet var correctButton: UIView!
    @IBOutlet var CompletedLabel: UILabel!
    @IBOutlet var MissedLabel: UILabel!
    @IBOutlet var CorrectLabel: UILabel!
    var cards = [Flashcard]()
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cardView.layer.cornerRadius = 8.0
        cardView.layer.borderColor = UIColor.gray.cgColor
        cardView.layer.borderWidth = 2.0
        //connect hard coded collection to sets
        cards = Flashcard.getHardCodedCollection()
        cardTermLabel.text = cards[currentIndex].term
        MissedLabel.text = "Cards Missed: \(numberOfMissed)"
        CorrectLabel.text = "Cards Correct: \(numberOfCorrect)"
        CompletedLabel.text = "Cards Completed: \(numberOfCompleted)"
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(StudyViewController.tapFunction))
        cardTermLabel.isUserInteractionEnabled = true
        cardTermLabel.addGestureRecognizer(tap)
    }

        @objc
        func tapFunction(sender:UITapGestureRecognizer) {
            cardDefLabel.text = cards[currentIndex].definition
        }
    
    @IBAction func skipButton(_ sender: Any) {
        goToNextCard()
    }
    
    //increments havent seen
    @IBAction func missedButton(_ sender: Any) {
        numberOfMissed = numberOfMissed + 1
        goToNextCard()
    }
    
    //increments have seen
    @IBAction func correctButton(_ sender: Any) {
        numberOfCorrect = numberOfCorrect + 1
        numberOfCompleted = numberOfCompleted + 1
        goToNextCard()
    }
    
    //resets the card and increments numbers
    func goToNextCard()
    {
        currentIndex = currentIndex + 1
        if currentIndex >= cards.count
        {
            currentIndex = 0
            numberOfMissed = 0
            numberOfCorrect = 0
            numberOfCompleted = 0
        }
        MissedLabel.text = "Cards Missed: \(numberOfMissed)"
        CorrectLabel.text = "Cards Correct: \(numberOfCorrect)"
        CompletedLabel.text = "Cards Completed: \(numberOfCompleted)"
        cardTermLabel.text = cards[currentIndex].term
    }
}
