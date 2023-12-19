<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/clientes">
    <html>
      <head>
        <link rel="stylesheet" href="../styles/style.css" />
        <title>Rentabilidad ordenada</title>
      </head>
      <body>
        <h2>Rentabilidad ordenada</h2>
        <table>
          <tr>
            <th>Nombre</th>
            <th>Ciudad</th>
            <th>Facturacion</th>
            <th>Pedidos</th>
            <th>Rentabilidad</th>
          </tr>
          <xsl:for-each select="cliente">
                    <xsl:sort
              select="format-number(substring-before(facturacion, '€') div pedidos, '.00')"
              order="descending" data-type="number" />
                    <tr>
              <td>
                <xsl:value-of select="nombre" />
              </td>
              <td>
                <xsl:value-of select="direccion/ciudad" />
              </td>
              <td>
                <xsl:value-of select="facturacion" />
              </td>
              <td>
                <xsl:value-of select="pedidos" />
              </td>
              <td>
                <xsl:value-of select="concat(format-number(substring-before(facturacion, '€') div pedidos, '.00'), ' € pedido')" />
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>