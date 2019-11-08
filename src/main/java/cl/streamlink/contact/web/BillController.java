package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Bill;
import cl.streamlink.contact.domain.Commande;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.BillService;
import cl.streamlink.contact.service.CommandeService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.BillDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
@RequestMapping("/ws/bills")
public class BillController {

    @Inject
    BillService billService;

    @Autowired
    CommandeService commandeService;

    /*
    @GetMapping(value = "all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all bills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Bill.class),
            @ApiResponse(code = 404, message = "bills not Found")})
    public List<BillDTO> getBills() {
        return billService.getBills();
    }
    */
    @GetMapping("all")
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<BillDTO> getBills(Pageable pageable, @RequestParam boolean fromAngular,
                                  @RequestParam(required = false) String value,
                                  @RequestParam(required = false) Sort.Direction dir) {
        if (fromAngular) {
            pageable = MiscUtils.convertFromAngularPage(pageable, dir, true);
        }
        return billService.searchBills(value, pageable);
    }

    @GetMapping(value = "one")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Bill Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = BillDTO.class),
            @ApiResponse(code = 404, message = "Bill with Ref not Found")
    })
    public BillDTO getBillByReference(@RequestParam(value = "billReference") String billReference) throws ContactApiException {
        return billService.getBillByReference(billReference);
    }

    @PostMapping(value = "commande")
    public List<Bill> getByCommande(@RequestBody Commande commande) {
        return billService.getByCommande(commande);
    }

    @PostMapping(value = "create")
    public Bill createBill(@RequestBody Bill bill) {

        Bill bi = billService.create(bill);
        Commande commande = commandeService.getById(bi.getCommande().getId());
        Double montant = Double.sum(commande.getMontantR(), bi.getUnitPrice());
        commandeService.updateMontant(commande.getId(), montant);
        return bi;
    }

    @GetMapping(value = "bill/one")
    public Bill getBillById(@RequestParam(value = "id") long id) {
        return billService.getBillById(id);
    }
}
