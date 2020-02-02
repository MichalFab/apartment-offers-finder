import { Component, OnInit } from '@angular/core';
import { Offer} from "../../report/offer";
import {OffersService} from "../../offers/offers.service";

@Component({
  selector: 'app-offers-list',
  templateUrl: './offers-list.component.html',
  styleUrls: ['./offers-list.component.scss']
})
export class OffersListComponent implements OnInit {
  offers : Offer[];
  isExpand:boolean=true;
  loading = true;
  constructor(private offersService: OffersService) { }

  ngOnInit() {
    this.getOffers();
  }

  private getOffers() {
    this.offersService.findAll()
      .subscribe(offers => {this.offers = offers; this.loading = false;});
  }

  private sortPrice() : Offer[] {
    return this.offers.sort((o1, o2) => o1.price - o2.price);
  }
  private sortM2Price() : Offer[] {
    return this.offers.sort((o1, o2) => o1.M2price - o2.M2price);
  }
  private filterPrivate() : Offer[] {
    return this.offers.filter(offer => offer.isPrivate === true);
  }
  private filterPriceChanged() : Offer[] {
    return this.offers.filter(offer => offer.isPriceDown === true);
  }

  isExpandToggle(){
    this.isExpand=!this.isExpand;
  }
}
