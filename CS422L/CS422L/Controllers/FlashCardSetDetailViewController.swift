//
//  FlashCardSetDetailActivity.swift
//  CS422L
//
//  Created by Jonathan Sligh on 2/3/21.
//
import UIKit

class FlashCardSetDetailViewController: UIViewController, UITableViewDelegate, UITableViewDataSource
{
    var cards: [Flashcard] = [Flashcard]()
    @IBOutlet var buttonView: UIView!
    @IBOutlet var tableView: UITableView!
    @IBOutlet var deleteButton: UIButton!
    @IBOutlet var studyButton: UIButton!
    @IBOutlet var addButton: UIButton!
    var selectedIndex: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cards = Flashcard.getHardCodedCollection()
        tableView.delegate = self
        tableView.dataSource = self
        //Long press to edit
        let longPressGesture = UILongPressGestureRecognizer(target: self, action: #selector(handleLongPress))
        longPressGesture.minimumPressDuration = 0.5
        self.tableView.addGestureRecognizer(longPressGesture)
        makeItPretty()
    }
    
    //handle long press function
        @objc func handleLongPress(longPressGesture: UILongPressGestureRecognizer) {
            //find out where the long press is
            let p = longPressGesture.location(in: self.tableView)
            let indexPath = self.tableView.indexPathForRow(at: p)
            //if long press is starting (this will also trigger on ending if you dont have this if statement)
            if longPressGesture.state == UIGestureRecognizer.State.began {
                selectedIndex = indexPath?.row ?? 0
                let sb = UIStoryboard(name: "Main", bundle: nil)
                // alertVC is used to pass details into the alert
                let alertVC = sb.instantiateViewController(identifier: "CustomAlertViewController") as! CustomAlertViewController
                alertVC.parentVC = self
                alertVC.modalPresentationStyle = .overCurrentContext
                self.present(alertVC, animated: true, completion: nil)
            }
        }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        createAlert(indexPath: indexPath)
    }
    
    //create normal alert
    func createAlert(indexPath: IndexPath)
    {
    let alert = UIAlertController(title: "\(cards[indexPath.row].term)", message: "\(cards[indexPath.row].definition)", preferredStyle: .alert)
        selectedIndex = indexPath.row
        alert.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: nil))
        alert.addAction(UIAlertAction(title: "Edit", style: .default, handler: {_ in
            alert.dismiss(animated: true, completion: {})
            self.createCustomEditAlert()
        }))
        self.present(alert, animated: true)
       }

        
    //create custom alert
    func createCustomEditAlert()
    {
        let sb = UIStoryboard(name: "Main", bundle: nil)
        let alertVC = sb.instantiateViewController(identifier: "CustomAlertViewController") as! CustomAlertViewController
        alertVC.parentVC = self
        alertVC.modalPresentationStyle = .overCurrentContext
        self.present(alertVC, animated: true, completion: nil)
    }
        
    //go to new viewcontroller
    @IBAction func takeQuiz(_ sender: Any) {
        performSegue(withIdentifier: "GoToQuiz", sender: self)
    }
    
    //adds card
    @IBAction func addCard(_ sender: Any) {
        let newCard = Flashcard()
        newCard.term = "Term \(cards.count + 1)"
        newCard.definition = "Definition \(cards.count + 1)"
        cards.append(newCard)
        tableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return cards.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CardCell", for: indexPath) as! FlashcardTableViewCell
        cell.flashcardLabel.text = cards[indexPath.row].term
        return cell
    }
    
    //just a function to make everything look nice
    func makeItPretty()
    {
        buttonView.layer.cornerRadius = 8.0
        buttonView.layer.borderColor = UIColor.purple.cgColor
        buttonView.layer.borderWidth = 2.0
        deleteButton.layer.cornerRadius = 8.0
        studyButton.layer.cornerRadius = 8.0
        addButton.layer.cornerRadius = 8.0
    }
    
    
}
