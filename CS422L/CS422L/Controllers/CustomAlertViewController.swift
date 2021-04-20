//
//  CustomAlertViewController.swift
//  CS422L
//
//  Created by Ryan Jones on 2/17/21.
//

import UIKit

class CustomAlertViewController: UIViewController {
    
    @IBOutlet var alertView: UIView!
    var parentVC: FlashCardSetDetailViewController!
    @IBOutlet var cardTerm: UITextField!
    @IBOutlet var cardDefinition: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupToLookPretty()
        //set text and description of dialog
        cardTerm.text = parentVC.cards[parentVC.selectedIndex].term
        cardDefinition.text = parentVC.cards[parentVC.selectedIndex].definition
    }
    
    @IBAction func save(_ sender: Any) {
        //change title/description to the new text and tell the tableView to reload
        parentVC.cards[parentVC.selectedIndex].term = cardTerm.text ?? ""
        parentVC.cards[parentVC.selectedIndex].definition = cardDefinition.text ?? ""
        parentVC.tableView.reloadData()
        self.dismiss(animated: true, completion: {})
    }
    
    @IBAction func carddelete(_ sender: Any) {
        //delete the movie and tell the tableview to reload
        parentVC.cards.remove(at: parentVC.selectedIndex)
        parentVC.tableView.reloadData()
        self.dismiss(animated: true, completion: {})
    }

    
    func setupToLookPretty()
    {
        alertView.layer.cornerRadius = 8.0
        alertView.layer.borderWidth = 3.0
        alertView.layer.borderColor = UIColor.gray.cgColor
        cardTerm.becomeFirstResponder()
    }
    
}
