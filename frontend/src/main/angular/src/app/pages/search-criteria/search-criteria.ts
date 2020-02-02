export class SearchCriteria {
  public constructor(public email: string, public city: string, public minRoom: number, public maxRoom: number, public minArea: number,
                     public maxArea: number, public minPrice: number, public maxPrice: number, public  minM2price: number, public maxM2price: number,
                     public minYear: number, public onlyPrivate: boolean){};

}
