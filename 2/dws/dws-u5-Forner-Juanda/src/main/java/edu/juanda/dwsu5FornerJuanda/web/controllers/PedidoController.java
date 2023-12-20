package edu.juanda.dwsu5FornerJuanda.web.controllers;

import edu.juanda.dwsu5FornerJuanda.models.dto.ClienteDTO;
import edu.juanda.dwsu5FornerJuanda.models.dto.PedidoDTO;
import edu.juanda.dwsu5FornerJuanda.services.PedidoService;
import edu.juanda.dwsu5FornerJuanda.services.ProveedorService;
import edu.juanda.dwsu5FornerJuanda.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private Validator validator;

    @GetMapping("/cliente/{idCliente}")
    public ModelAndView findByCliente(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("pedido/pedidos");

        mav.addObject("pedidosDTO", pedidoService.findByCliente(idCliente));
        mav.addObject("esCliente", true);

        return mav;
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ModelAndView findByProveedor(@PathVariable Long idProveedor) {
        ModelAndView mav = new ModelAndView("pedido/pedidos");

        mav.addObject("pedidosDTO", pedidoService.findByProveedor(idProveedor));
        mav.addObject("esCliente", false);

        return mav;
    }

    @GetMapping("/cliente/{idCliente}/create")
    public ModelAndView create(@PathVariable Long idCliente) {
        ModelAndView mav = new ModelAndView("pedido/pedido-form");

        PedidoDTO pedidoDTO = new PedidoDTO();
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(idCliente);
        pedidoDTO.setClienteDTO(clienteDTO);

        mav.addObject("pedidoDTO", pedidoDTO);
        mav.addObject("proveedoresDTO", proveedorService.findAll());
        mav.addObject("add", true);

        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute PedidoDTO pedidoDTO) {
        ArrayList<String> errores = validator.validate(pedidoDTO);

        if (!errores.isEmpty()) {
            ModelAndView mav = new ModelAndView("pedido/pedido-form");
            mav.addObject("pedidoDTO", pedidoDTO);
            mav.addObject("proveedoresDTO", proveedorService.findAll());
            mav.addObject("errores", errores);
            mav.addObject("add", true);

            return mav;
        }

        pedidoService.save(pedidoDTO);
        return new ModelAndView("redirect:/pedidos/cliente/" + pedidoDTO.getClienteDTO().getId());
    }

    @GetMapping("delete/{id}/{who}/{idWho}")
    public ModelAndView delete(@PathVariable Long id, @PathVariable String who, @PathVariable Long idWho) {
        pedidoService.delete(id);
        return new ModelAndView("redirect:/pedidos/" + who + "/" + idWho);
    }
}
